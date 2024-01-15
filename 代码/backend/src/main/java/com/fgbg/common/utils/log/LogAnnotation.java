package com.fgbg.common.utils.log;

import javax.validation.constraints.NotEmpty;
import java.lang.annotation.*;

/**
 * 日志注解
 */
 //ElementType.TYPE代表可以放在类上面  method代表可以放在方法上
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    /**
     * 枚举方法类型
     */
    enum MethodType{
        CONTROLLER,
        SERVICE,
        DAO
    }

    String module() default "";

    String operation() default "";

    @NotEmpty(message = "方法类型必填")
    MethodType methodType();
}

