package sample.data.jpa.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class aspectLogger {

    Logger logger = LoggerFactory.getLogger(String.valueOf(aspectLogger.class));

    /**
     * Methode qui s'execute à chaque appel de fonction contenu dans le package "sample.data.jpa"
     */
    @Pointcut("execution(public String sample.data.jpa.*.*.*(..))")
    public void generalPointcut() {}

    /**
     * Instruction avant l'execution de chaque fonction de la methode "generalPointcut"
     * @param joinpoint
     */
    @Before("generalPointcut()")
    public void InfoLog(JoinPoint joinpoint) {
        logger.info("Debut d'appel de la méthode");
        logger.info(joinpoint.getTarget().getClass().getSimpleName()+" : " + joinpoint.getSignature().getName());
        logger.info("L'adresse mémoire est : " + joinpoint.getTarget().toString());
    }

    /**
     *Instruction apres l'execution de chaque fonction de la methode "generalPointcut"
     * @param joinpoint
     */
    @After("generalPointcut()")
    public void log1(JoinPoint joinpoint) {
        logger.info("Fin d'appel de la méthode");
        logger.info(joinpoint.getTarget().getClass().getSimpleName()+" : " + joinpoint.getSignature().getName());
    }
}