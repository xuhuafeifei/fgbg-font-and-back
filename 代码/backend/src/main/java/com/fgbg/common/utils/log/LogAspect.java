package com.fgbg.common.utils.log;

import com.alibaba.fastjson.JSON;
import com.fgbg.common.utils.HttpContextUtils;
import com.fgbg.common.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ljm
 * @Date 2021/10/18 21:01
 * @Version 1.0
 */
@Component
@Aspect //切面 定义了通知和切点的关系
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.fgbg.common.utils.log.LogAnnotation)")
    public void pt(){
    }

    //环绕通知
    @Around("pt()")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        MutablePair<LogAnnotation.MethodType, String> res = recordBefore(point);
        long beginTime = System.currentTimeMillis();
        log.info("*********************** {} business start *******************", res.getValue());
        //执行方法
        Object result = point.proceed();
        log.info("*********************** {} business end *********************", res.getValue());
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        recordAfter(time, res, result);
        return result;
    }

    /**
     * 方法执行前记录日志信息,并返回当前方法类型
     * @param joinPoint
     * @return MethodType.controller / service; String methodName
     */
    private MutablePair<LogAnnotation.MethodType, String> recordBefore(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        LogAnnotation.MethodType methodType = logAnnotation.methodType();
        if (methodType == LogAnnotation.MethodType.CONTROLLER) {
            log.info("=====================log start================================");
        }else {
            log.info("---------------------{} service start-------------------------", method.getName());
        }
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operation());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        // 处理ServletRequest,ServletResponse无法序列化对象
        List<Object> argsList=new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            // 如果参数类型是请求和响应的http，则不需要拼接【这两个参数，使用JSON.toJSONString()转换会抛异常】
            if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse) {
                continue;
            }
            argsList.add(args[i]);
        }
        String body = JSON.toJSONString(argsList);
        log.info("params:{}",body);

        //获取request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IPUtils.getIpAddr(request));

        return new MutablePair<>(methodType, methodName);
    }

    /**
     * 方法执行结束后,记录日志
     * @param time
     * @param res
     * @param result
     */
    private void recordAfter(long time, MutablePair<LogAnnotation.MethodType, String> res, Object result) {
        log.info("excute time : {} ms",time);
        LogAnnotation.MethodType methodType = res.getKey();
        String methodName = res.getValue();

        if (methodType == LogAnnotation.MethodType.CONTROLLER) {
            log.info("result : {}", JSON.toJSONString(result));
            log.info("=====================log end================================");
        }else {
            log.info("---------------------{} service end-------------------------", methodName);
        }
    }

}