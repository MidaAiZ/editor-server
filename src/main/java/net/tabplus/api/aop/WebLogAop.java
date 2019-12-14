package net.tabplus.api.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.tabplus.api.utils.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author www.exception.site (exception 教程网)
 * @date 2019/2/12
 * @time 14:03
 * @discription
 **/
@Slf4j
@Aspect
@Component
public class WebLogAop {
    /** 以 controller 包下定义的所有请求为切入点 */
    @Pointcut("execution(public * *..controller..*.*(..))")
    public void webLog() {}

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        // 开始打印请求日志
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Object[] args = joinPoint.getArgs();
//        Object[] arguments  = new Object[args.length];
//        for (int i = 0; i < args.length; i++) {
//            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
//                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
//                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
//                continue;
//            }
//            arguments[i] = args[i];
//        }
//        String paramter = "";
//        if (arguments != null) {
//            try {
//                paramter = JSONObject.toJSONString(arguments);
//            } catch (Exception e) {
//                paramter = arguments.toString();
//            }
//        }
//
//        // 打印请求相关参数
//        log.info("========================================== Start ==========================================");
//        // log.info(request.getMethod() + " " + request.getRequestURL().toString() + "\n IP: " + request.getRemoteAddr() + "\n Class Method: " + joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName() + "\n Request Args : {}", JSONObject.toJSONString(joinPoint.getArgs()));
//        // 打印请求 url
//        log.info("URL            : {}", request.getRequestURL().toString());
//        // 打印 Http method
//        log.info("HTTP Method    : {}", request.getMethod());
//        // 打印调用 controller 的全路径以及执行方法
//        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//        // 打印请求的 IP
//        log.info("IP             : {}", request.getRemoteAddr());
//        // 打印请求入参
//        log.info("Request Args   : {}", paramter);
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
//        log.info("=========================================== End ===========================================");
        // 每个请求之间空一行
//        log.info("");
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            // 获取执行结果
            Result result = (Result) proceedingJoinPoint.proceed();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();

            Object[] args = proceedingJoinPoint.getArgs();
            Object[] arguments  = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }
                arguments[i] = args[i];
            }
            String paramter = "";
            if (arguments != null) {
                try {
                    paramter = JSONObject.toJSONString(arguments);
                } catch (Exception e) {
                    paramter = arguments.toString();
                }
            }

            // 打印请求相关参数
            log.info("\n{} {} \nIP: {} \nClass Method: {}.{} \nRequest Args: {} \nResponse: {}, Code: {} \nTime-Consuming: {}ms",
                    request.getMethod(), request.getRequestURL(),
                    request.getRemoteAddr(),
                    proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(),
                    paramter, response.getStatus(), result.getCode(),
                    System.currentTimeMillis() - startTime
            );
            return result;
        } catch (Exception e) {
            return proceedingJoinPoint.proceed();
        }
    }
}

