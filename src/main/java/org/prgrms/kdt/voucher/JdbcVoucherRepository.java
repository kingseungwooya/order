package org.prgrms.kdt.voucher;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("dev")
public class JdbcVoucherRepository implements VoucherRepository {
    // select 시 데이터가 없을 수도 있음을 항상 생각하고 Optional을 사용하자.

    private final Map<UUID, Voucher> storage = new ConcurrentHashMap<>();

    // Null일 경우 Optional.Empty를 반환하도록 하자
    @Override
    public Optional<Voucher> findById(UUID voucherId) {
        return Optional.ofNullable(storage.get(voucherId));
    }

    @Override
    public Voucher insert(Voucher voucher) {
        storage.put(voucher.getVoucherId(), voucher);
        return voucher;
    }
}
