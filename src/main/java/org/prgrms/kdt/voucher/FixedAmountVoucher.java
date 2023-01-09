package org.prgrms.kdt.voucher;

import java.util.UUID;

public class FixedAmountVoucher implements Voucher {
    private static final long MAX_VOUCHER_AMOUNT = 10000;

    private final UUID voucherId;
    private final long amount;

    public FixedAmountVoucher(UUID voucherId, long amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount Should Be Positive");
        if (amount == 0) throw new IllegalArgumentException("Amount Should Be Positive");
        if (amount > MAX_VOUCHER_AMOUNT)
            throw new IllegalArgumentException("Amount Should be less than %d".formatted(MAX_VOUCHER_AMOUNT));
        this.voucherId = voucherId;
        this.amount = amount;
    }

    @Override
    public UUID getVoucherId() {
        return voucherId;
    }

    public long discount(long beforeDiscount) {
        var discountAmount = beforeDiscount - amount;
        return (discountAmount < 0) ? 0 : discountAmount;
    }

}
