package org.prgrms.kdt;

import org.prgrms.kdt.AppConfiguration;
import org.prgrms.kdt.order.OrderItem;
import org.prgrms.kdt.order.OrderProperties;
import org.prgrms.kdt.order.OrderService;
import org.prgrms.kdt.voucher.FixedAmountVoucher;
import org.prgrms.kdt.voucher.JdbcVoucherRepository;
import org.prgrms.kdt.voucher.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTester {

    private static final Logger logger = LoggerFactory.getLogger(OrderTester.class);
    public static void main(String[] args) {
        // Configuration Metadata를 java 기반으로~
        var applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfiguration.class);
        var environment = applicationContext.getEnvironment();
        environment.setActiveProfiles("local");
        applicationContext.refresh();

        // property 가져와보기
        /*
        var version = environment.getProperty("kdt.version");
        var minimumOrderAmount = environment.getProperty("kdt.minimum-order-amount" , Integer.class);
        var supportVendors = environment.getProperty("kdt.support-vendors", List.class);  */
       // var description = environment.getProperty("kdt.description");
        var orderProperties = applicationContext.getBean(OrderProperties.class);
        System.out.println(MessageFormat.format("version -> {0}", orderProperties.getVersion()));
        System.out.println(MessageFormat.format("minimumOrderAmount -> {0}", orderProperties.getMinimumOrderAmount()));
        System.out.println(MessageFormat.format("supportVendors -> {0}", orderProperties.getDescription()));

        var customerID = UUID.randomUUID();
        var voucherRepo = applicationContext.getBean(VoucherRepository.class);
        var voucher = voucherRepo.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));
        /*
         var voucherRepo = BeanFactoryAnnotationUtils
                .qualifiedBeanOfType(applicationContext.getBeanFactory(), VoucherRepository.class, "memory");
        var voucherRepo2 = BeanFactoryAnnotationUtils
                .qualifiedBeanOfType(applicationContext.getBeanFactory(), VoucherRepository.class, "memory");
        System.out.println(voucherRepo.equals(voucherRepo2));*/


        logger.info("logger name => {}", logger.getName());
        logger.info(MessageFormat.format("is Jdbc Repo -> {0}", voucherRepo instanceof JdbcVoucherRepository));
        logger.info(MessageFormat.format("is Jdbc Repo -> {0}", voucherRepo.getClass().getCanonicalName()));


        var orderService = applicationContext.getBean(OrderService.class); // MetaData에 등록된 Bean을 갖고온다.
        var orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }};
        var order = orderService.createOrder(customerID, orderItems, voucher.getVoucherId());
        Assert.isTrue(order.totalAmount() == 90L, MessageFormat.format("totalAmount {0} is not 100L", order.totalAmount()));

        applicationContext.close();
    }
}
