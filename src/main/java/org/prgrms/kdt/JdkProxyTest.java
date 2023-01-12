package org.prgrms.kdt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Calculator {
    int add(int a, int b);
}

class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}

class LoggingInvocationHandler implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    private final Object target;

    LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        logger.info("{} executed", method.getName());
        return method.invoke(target, objects);
    }
}

public class JdkProxyTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    public static void main(String[] args) {
        var calculator = new CalculatorImpl();
        var newProxyInstance = (Calculator) Proxy.newProxyInstance(
                LoggingInvocationHandler.class.getClassLoader(),
                new Class[]{Calculator.class}, new LoggingInvocationHandler(calculator)
        );
        var result = newProxyInstance.add(1, 2);
        logger.info("Add -> {}", result);
    }
}
