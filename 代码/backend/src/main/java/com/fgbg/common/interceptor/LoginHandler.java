package com.fgbg.common.interceptor;//package com.fgbg.common.interceptor;
//
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.google.gson.Gson;
//import com.fgbg.common.exception.ErrorCode;
//import com.fgbg.common.utils.JWTUtils;
//import com.fgbg.common.utils.R;
//import com.fgbg.common.utils.RedisKey;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class LoginHandler implements HandlerInterceptor {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Resource
//    private Gson gson;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // debug
//        log.info("PreHandle start");
//        if (!(handler instanceof HandlerMethod)){
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        if(handlerMethod.hasMethodAnnotation(NoAuth.class)){
//            return true;
//        }
//
//        String token = request.getHeader("Authorization");
//        if (StringUtils.isBlank(token)){
//            log.info("Authorization没有携带token信息,未登录");
//            log.info("request:{}", request);
//            return noLoginResponse(response);
//        }
//        token = token.replace("Bearer ", "");
//        log.info("{}", token);
//        boolean verify = JWTUtils.verify(token);
//        if (!verify){
//            log.info("token非法:{}", token);
//            return noLoginResponse(response);
//        }
//        String userJson = redisTemplate.opsForValue().get(RedisKey.TOKEN_KEY + token);
//        if (StringUtils.isBlank(userJson)){
//            log.info("没有获取到服务器中存储的userJson,请重新登录");
//            return LoginTimeOutResponse(response);
//        }
//        UserDto userDto = gson.fromJson(userJson, UserDto.class);
//        UserThreadLocal.put(userDto);
//        // 此方法必须为主管才能调用
//        /*
//        if (handlerMethod.hasMethodAnnotation(NoManager.class) && !userDto.getRole().equals(Constant.MANAGER)) {
//            return noManagerResponse(response);
//        }
//        if (userDto.getRole().equals(Constant.MANAGER)) {
//            log.info("主管登录:{}", userDto);
//        }else if (userDto.getRole().equals(Constant.USER)) {
//            log.info("用户登录:{}",userDto);
//        }
//         */
//        return true;
//    }
//
//    private boolean LoginTimeOutResponse(HttpServletResponse response) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(gson.toJson(R.error(ErrorCode.LOGIN_TIMEOUT)));
//        return false;
//    }
//
//    private boolean noManagerResponse(HttpServletResponse response) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(gson.toJson(R.error(ErrorCode.NOT_MANAGER)));
//        return false;
//    }
//
//    private boolean noLoginResponse(HttpServletResponse response) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(gson.toJson(R.error(ErrorCode.NOT_LOGIN)));
//        return false;
//    }
//}
