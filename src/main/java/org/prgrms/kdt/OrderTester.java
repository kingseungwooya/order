package org.prgrms.kdt;

import org.prgrms.kdt.AppConfiguration;
import org.prgrms.kdt.order.OrderItem;
import org.prgrms.kdt.order.OrderService;
import org.prgrms.kdt.voucher.FixedAmountVoucher;
import org.prgrms.kdt.voucher.VoucherRepository;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {
        // Configuration Metadata를 java 기반으로~
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var orderService = applicationContext.getBean(OrderService.class); // MetaData에 등록된 Bean을 갖고온다.

        // property 가져와보기
        var environment = applicationContext.getEnvironment();
        var version = environment.getProperty("kdt.version");
        var minimumOrderAmount = environment.getProperty("kdt.minimum-order-amount" , Integer.class);
        var supportVendors = environment.getProperty("kdt.support-vendors", List.class);
        System.out.println(MessageFormat.format("version -> {0}", version));
        System.out.println(MessageFormat.format("minimumOrderAmount -> {0}", minimumOrderAmount));
        System.out.println(MessageFormat.format("supportVendors -> {0}", supportVendors));

        var customerID = UUID.randomUUID();
        // 이렇게 사용할 시 구현체가 두개이기 때문에 어떤것을 Bean으로 갖고와야할지 모른다.
        // var voucherRepo = applicationContext.getBean(VoucherRepository.class);
        var voucherRepo = BeanFactoryAnnotationUtils
                .qualifiedBeanOfType(applicationContext.getBeanFactory(), VoucherRepository.class, "memory");
        var voucherRepo2 = BeanFactoryAnnotationUtils
                .qualifiedBeanOfType(applicationContext.getBeanFactory(), VoucherRepository.class, "memory");
        System.out.println(voucherRepo.equals(voucherRepo2));

        var voucher = voucherRepo.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));

        var orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }};
        var order = orderService.createOrder(customerID, orderItems, voucher.getVoucherId());
        Assert.isTrue(order.totalAmount() == 90L, MessageFormat.format("totalAmount {0} is not 100L", order.totalAmount()));

        applicationContext.close();
    }
}
