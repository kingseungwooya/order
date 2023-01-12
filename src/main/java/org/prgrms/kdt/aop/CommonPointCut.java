package org.prgrms.kdt.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {

    @Pointcut("execution(public * org.prgrms.kdt..*service.*(..))")
    public void servicePublicMethodPointCut() {

    }
    @Pointcut("execution(public * org.prgrms.kdt..*service.*(..))")
    public void repositoryMethodPointCut() {

    }
    @Pointcut("execution(public * org.prgrms.kdt..*Repository.insert(..))")
    public void repositoryInsertMethodPointCut() {

    }
}
