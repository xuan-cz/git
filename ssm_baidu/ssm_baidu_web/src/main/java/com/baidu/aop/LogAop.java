package com.baidu.aop;

import com.baidu.controller.SysLogController;
import com.baidu.domain.SysLog;
import com.baidu.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 记录系统访问日志
 * 注入ioc容器，切面类
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Class clazz; // 获取执行的class对象
    private Method method; // 获取执行的方法对象
    private Date visitTime;// 开始时间

    // 前置通知
    @Before("execution(* com.baidu.controller.*.*(..))")
    public void doBefore(JoinPoint point) throws NoSuchMethodException {
        // 获取开始时间
        visitTime = new Date();
        // 具体要访问的类
        clazz = point.getTarget().getClass();
        // 具体访问的方法的名字
        String methodName = point.getSignature().getName();
        // 获取访问的方法参数
        Object[] args = point.getArgs();

        //获取具体执行的方法的Method对象
        if(args == null || args.length == 0){
            // 空参
            method = clazz.getMethod(methodName);
        }else{
            // 有参数,获取参数的每一个clazz对象
            Class[] clazzArgs = new Class[args.length];
            for (int i = 0; i < clazzArgs.length; i++) {
                clazzArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,clazzArgs);
        }
    }
    // 后置通知
    @After("execution(* com.baidu.controller.*.*(..))")
    public void doAfter(JoinPoint point) throws Exception {
        // 获取访问总时长
        long time = System.currentTimeMillis() - visitTime.getTime();

        // 如果操作的是日志的handler，之间结束方法，不存储日志信息
        if(clazz == SysLogController.class){
            return;
        }
        String url = null;
        // 获取url
        if(clazz != null && method != null && clazz != LogAop.class){
            // 获取类上的@RequestMapping注解对象
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation != null){
                // 获取注解对象的value数组
                String[] clazzValue = clazzAnnotation.value();
                // 获取方法上的@RequestMapping注解对象
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = clazzValue[0] + methodValue[0];

                    // 获取访问者的Ip地址
                    String ip = request.getRemoteAddr();

                    // 获取访问者的用户名
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    // 创建SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setUsername(username);

                    // 调用service完成添加操作
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
