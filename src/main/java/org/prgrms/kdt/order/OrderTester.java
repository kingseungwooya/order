package org.prgrms.kdt.order;

import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {
        var customerID = UUID.randomUUID();
        var orderContext = new OrderContext();
        var orderService = orderContext.orderService();
        var orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }};
        var order = orderService.createOrder(customerID, orderItems);
        Assert.isTrue(order.totalAmount() == 100L, MessageFormat.format("totalAmount {0} is not 100L", order.totalAmount()));


    }
}
