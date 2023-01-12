package org.prgrms.aoptest;

import org.prgrms.kdt.AppConfiguration;
import org.prgrms.kdt.aop.TrackTime;
import org.prgrms.kdt.order.OrderItem;
import org.prgrms.kdt.order.OrderService;
import org.prgrms.kdt.order.OrderStatus;
import org.prgrms.kdt.voucher.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@ActiveProfiles("dev")
public class AopTest {

    @Autowired
    ApplicationContext context;

    @Configuration
    @ComponentScan (
            basePackages = {"org.prgrms.kdt.voucher", "org.prgrms.kdt.aop"}
    )
    @EnableAspectJAutoProxy
    static class Config {

    }
    @Autowired
    VoucherRepository voucherRepository;

    @Test
    @DisplayName("AOP TEST ")
    public void testOrderService() {
// Given
        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
        voucherRepository.insert(fixedAmountVoucher);


    }
}
