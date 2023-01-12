package org.prgrms.kdt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    // porint cut에 명시되어있는것을 기반으로 adviser가 동작한다.
    @Around("org.prgrms.kdt.aop.CommonPointCut.repositoryInsertMethodPointCut()") // 쓰는 방법 다시 보기
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Before Method Called. {}", joinPoint.getSignature().toString());
        var result = joinPoint.proceed();
        logger.info("After Method Called. {}", result);
        return result;

    }

}
