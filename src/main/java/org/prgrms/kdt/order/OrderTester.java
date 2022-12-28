package org.prgrms.kdt.order;

import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {
    public static void main(String[] args) {
        var customerID = UUID.randomUUID();
        var orderItems = new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }};
        var order = new Order(UUID.randomUUID(), customerID, orderItems, 10L);

        Assert.isTrue(order.totalAmount() == 90L,
                MessageFormat.format("totalAmount {0} is not 90L", order.totalAmount()));

    }
}
