package org.prgrms.kdt;

import org.prgrms.kdt.configuration.VersionProvider;
import org.prgrms.kdt.configuration.YamlPropertiesFactory;
import org.prgrms.kdt.order.Order;
import org.prgrms.kdt.voucher.Voucher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**  ioc container !!! 객체들간의 의존관계뿐만 아니라 생성과 파괴 모두 다 이루어진다.
 * VoucherService, Repository , OrderService, Repository 생성에 대한 책임을 맡게된다.
 * 각각의 Service와 Repository간의 의존관계를 맺는 역할을 담당. 컴포넌트 생성을 위한 메서드들이 있다.
 */

/**
 * Configuration을 통해 이 클래스는 Bean의 도면 역할을 한다고 지정해줌.
 */
@Configuration
@ComponentScan(basePackages = {"org.prgrms.kdt.configuration", "org.prgrms.kdt.order", "org.prgrms.kdt.voucher"})
       // excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {CircularConfig.class})}) // basePackegesClass를 이용해 해당 클래스가 속해있는 패키지만 참조한다. .
@PropertySource(value = "application.yaml", factory = YamlPropertiesFactory.class)
@EnableConfigurationProperties
public class AppConfiguration {


}


