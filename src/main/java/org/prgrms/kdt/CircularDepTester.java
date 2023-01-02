package org.prgrms.kdt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * A가 B를 참조하고 B가 A를 참조하는 순환 의존관계가 생성되며 Been CurrentlyInCreationException 발생
 */
class A {
    private final B b;

    A(B b) {
        this.b = b;
    }
}
class B {
    private final A a;

    B(A a) {
        this.a = a;
    }
}
class CircularConfig {
    @Bean
    public  A a (B b) {
        return new A(b);
    }
    @Bean B b(A a) {
        return new B(a);
    }

}

public class CircularDepTester {
    public static void main(String[] args) {
        var annotationConfigApplicationContext = new AnnotationConfigApplicationContext(CircularConfig.class);

    }
}
