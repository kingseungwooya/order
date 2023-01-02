package org.prgrms.kdt.voucher;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Qualifier("memory")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MemoryVoucherRepository implements VoucherRepository, InitializingBean, DisposableBean {
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

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct Called!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet Called!");

    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy Called!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy Called!");

    }
}
