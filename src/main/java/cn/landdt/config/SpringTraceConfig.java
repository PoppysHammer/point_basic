package cn.landdt.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Configuration
public class SpringTraceConfig {

    @Pointcut("execution(* cn.landdt.*.*(..))")

    public void monitor() {
    }

    @Bean
    public Advisor init() {
        CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
        customizableTraceInterceptor.setUseDynamicLogger(true);

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("cn.landdt.config.SpringTraceConfig.monitor()");
        return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor);
    }

}
