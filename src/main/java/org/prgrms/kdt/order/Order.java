package org.prgrms.kdt.order;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final UUID customerID;
    private final List<OrderItem> orderItems;
    private FixedAmountVoucher fixedAmountVoucher;
    private OrderStatus orderStatus = OrderStatus.ACCEPTED;

    public Order(UUID orderId, UUID customerID, List<OrderItem> orderItems, long discountAmount) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.orderItems = orderItems;
        this.fixedAmountVoucher = new FixedAmountVoucher(discountAmount);
    }

    public long totalAmount() {
        Long beforeDiscount =  orderItems.stream()
                .map(i -> i.getProductPrice() * i.getQuantity())
                .reduce(0L, Long::sum);
        return fixedAmountVoucher.discount(beforeDiscount);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
