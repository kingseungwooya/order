package org.prgrms.kdt.order;

import java.util.Optional;
import java.util.UUID;
//
public interface VoucherRepository {
    // select 시 데이터가 없을 수도 있음을 항상 생각하고 Optional을 사용하자.
    Optional<Voucher> findById(UUID voucherId);
    Voucher insert(Voucher voucher);
}
