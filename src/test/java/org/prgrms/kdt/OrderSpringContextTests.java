package org.prgrms.kdt;

import org.prgrms.kdt.order.OrderItem;
import org.prgrms.kdt.order.OrderService;
import org.prgrms.kdt.order.OrderStatus;
import org.prgrms.kdt.voucher.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(classes = {AppConfiguration.class})
@ActiveProfiles("dev")
public class OrderSpringContextTests {

    @Autowired
    ApplicationContext context;

    @Autowired
    OrderService orderService;

    @Autowired
    VoucherRepository voucherRepository;
    @Test
    @DisplayName("applicationContext가 생성되어야 한다.")
    public void testApplicationContext() {
        assertThat(context, notNullValue());
    }

    @Test
    @DisplayName("VooucherRepo 가 빈으로 등록되어 있어야 한다. ")
    public void testVoucherRepoCreation() {
        var bean = context.getBean(VoucherRepository.class);
        assertThat(bean, notNullValue());
    }
    @Test
    @DisplayName("orderservice를 사용해서 주문을 생성할 수 있다. ")
    public void testOrderService() {
// Given
        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
        voucherRepository.insert(fixedAmountVoucher);

        // When
        var order = orderService.createOrder(
                UUID.randomUUID(),
                List.of(new OrderItem(UUID.randomUUID(), 200, 1)),
                fixedAmountVoucher.getVoucherId());

        // Then
        assertThat(order.totalAmount(), is(100L));
        assertThat(order.getVoucher().isEmpty(), is(false));
        assertThat(order.getVoucher().get().getVoucherId(), is(fixedAmountVoucher.getVoucherId()));
        assertThat(order.getOrderStatus(), is(OrderStatus.ACCEPTED));
    }
}
