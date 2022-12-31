package org.prgrms.kdt.order;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.text.Annotation;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {
        // Configuration Metadata를 java 기반으로~
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var orderService = applicationContext.getBean(OrderService.class); // MetaData에 등록된 Bean을 갖고온다.

        var customerID = UUID.randomUUID();
        var orderContext = new AppConfiguration();
        var orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }};
        var order = orderService.createOrder(customerID, orderItems);
        Assert.isTrue(order.totalAmount() == 100L, MessageFormat.format("totalAmount {0} is not 100L", order.totalAmount()));


    }
}
