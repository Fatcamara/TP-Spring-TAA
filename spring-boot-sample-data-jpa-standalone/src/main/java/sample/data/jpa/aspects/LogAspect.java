package sample.data.jpa.aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LogAspect {
/**
    Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Pointcut("execution(* sample.data.jpa.*.*.*(..))")
    private void generalPointcut(){
    }
    @Before("generalPointcut()")
    public void infoLog(JoinPoint joinPoint){
        logger.info(joinPoint.getTarget().getClass().getSimpleName()+" : " + joinPoint.getSignature().getName());
    }
*/
}