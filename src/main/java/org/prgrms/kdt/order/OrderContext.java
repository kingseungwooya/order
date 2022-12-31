package org.prgrms.kdt.order;

import java.util.Optional;
import java.util.UUID;

/**  ioc container !!! 객체들간의 의존관계뿐만 아니라 생성과 파괴 모두 다 이루어진다.
 * VoucherService, Repository , OrderService, Repository 생성에 대한 책임을 맡게된다.
 * 각각의 Service와 Repository간의 의존관계를 맺는 역할을 담당. 컴포넌트 생성을 위한 메서드들이 있다.
 */
public class OrderContext {

    public VoucherRepository voucherRepository() {
        return new VoucherRepository() {
            @Override
            public Optional<Voucher> findById(UUID voucherId) {
                return Optional.empty();
            }
        };
    }

    public OrderRepository orderRepository() {
        return new OrderRepository() {
            @Override
            public void insert(Order order) {

            }
        };
    }

    public VoucherService voucherService() {
        return new VoucherService(voucherRepository());
    }

    public OrderService orderService() {
        return new OrderService(voucherService(), orderRepository());
    }
}
