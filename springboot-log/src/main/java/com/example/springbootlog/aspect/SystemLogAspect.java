package com.example.springbootlog.aspect;

import com.example.springbootlog.annotation.SystemLog;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class SystemLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Pointcut("@annotation(com.example.springbootlog.annotation.SystemLog)")
    public void log() {}

    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();

        Object result=joinPoint.proceed();
        logger.info("Response："+new Gson().toJson(result));
        logger.info("耗时："+(System.currentTimeMillis()-startTime));

        return result;
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("==================Start=================");
        logger.info("URL：" + request.getRequestURL().toString());
        logger.info("Description：" + getLogValue(joinPoint));
        logger.info("Method：" + request.getMethod().toString());

        //打印controller全路径及method
        logger.info("Class Method：" + joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());
        logger.info("客户端IP：" + request.getRemoteAddr());

        logger.info("请求参数：" + new Gson().toJson(joinPoint.getArgs()));

    }

    private String getLogValue(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        SystemLog systemLog = method.getAnnotation(SystemLog.class);

        return systemLog.value();
    }

    @After("log()")
    public void doAfter() {
        logger.info("==================End=================");
    }
}
