package org.prgrms.kdt.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

/**  ioc container !!! 객체들간의 의존관계뿐만 아니라 생성과 파괴 모두 다 이루어진다.
 * VoucherService, Repository , OrderService, Repository 생성에 대한 책임을 맡게된다.
 * 각각의 Service와 Repository간의 의존관계를 맺는 역할을 담당. 컴포넌트 생성을 위한 메서드들이 있다.
 */

/**
 * Configuration을 통해 이 클래스는 Bean의 도면 역할을 한다고 지정해줌.
 */
@Configuration
@ComponentScan
public class AppConfiguration {


}
