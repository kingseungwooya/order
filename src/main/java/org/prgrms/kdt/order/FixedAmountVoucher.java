package org.prgrms.kdt.order;

public class FixedAmountVoucher {
    private final long amount;

    public FixedAmountVoucher(long amount) {
        this.amount = amount;
    }

    public long discount(long beforeDiscount) {
        return beforeDiscount - amount;
    }

}
