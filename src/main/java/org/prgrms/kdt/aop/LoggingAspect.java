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

    // 특정 애노테이션이 붙은 곳에만 이 함수 실행
    @Around("@annotation(org.prgrms.kdt.aop.TrackTime)") // 쓰는 방법 다시 보기
    public Object log2(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = joinPoint.proceed();
        var endTime = System.nanoTime() - startTime;
        logger.info("After Method Called. => {} and time taken By => {} nanoseconds", result, endTime);
        return result;

    }

}
