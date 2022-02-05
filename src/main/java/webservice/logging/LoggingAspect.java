package webservice.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(webservice.service.GroupManager)")
    public void serviceMethods() {
    }

    @Pointcut("within(webservice.controllers.MainController)")
    public void controllerMethods() {
    }

    @Pointcut("within(webservice.jsonUtils.ReadJson)")
    public void jsonMethods() {
    }

    @Around("controllerMethods()")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.debug("controller method has been called - " + joinPoint.getSignature().getName());
        Object proceed = joinPoint.proceed();
        LOGGER.debug("controller method completed successfully - " + joinPoint.getSignature().getName());
        return proceed;
    }

    @Around("serviceMethods()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.debug("method call - " + joinPoint.getSignature());
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.debug("method - " + joinPoint.getSignature().getName() +  " completed in " + executionTime + " ms");
        LOGGER.debug("return value - " + proceed.toString());
        return proceed;
    }

    @Before("jsonMethods()")
    public void logJsonMethodsBefore(JoinPoint joinPoint) {
        LOGGER.debug("reading json - " + joinPoint.getSignature().getName());
    }

    @AfterReturning("jsonMethods()")
    public void logJsonMethodsAfter(JoinPoint joinPoint) {
        LOGGER.debug("json data read successfully");
    }

    @AfterThrowing(value = "jsonMethods()", throwing = "e")
    public void logJsonMethodsAfterEx(JoinPoint joinPoint, Throwable e) {
        LOGGER.debug("error in method - " + joinPoint.getSignature());
        LOGGER.error(e);
    }
}
