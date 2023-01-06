package org.prgrms.kdt;

import org.prgrms.kdt.order.OrderProperties;
import org.prgrms.kdt.voucher.FixedAmountVoucher;
import org.prgrms.kdt.voucher.VoucherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.MessageFormat;
import java.util.UUID;

@SpringBootApplication
@ComponentScan(basePackages = {"org.prgrms.kdt.configuration", "org.prgrms.kdt.order", "org.prgrms.kdt.voucher"})
public class OrderApplication {

    public static void main(String[] args) {
        var springApplication = new SpringApplication(OrderApplication.class);
        springApplication.setAdditionalProfiles("local");

        var applicationContext =   springApplication.run(args);
        var orderProperties = applicationContext.getBean(OrderProperties.class);
        System.out.println(MessageFormat.format("version -> {0}", orderProperties.getVersion()));
        System.out.println(MessageFormat.format("minimumOrderAmount -> {0}", orderProperties.getMinimumOrderAmount()));
        System.out.println(MessageFormat.format("supportVendors -> {0}", orderProperties.getDescription()));

        var customerID = UUID.randomUUID();
        var voucherRepo = applicationContext.getBean(VoucherRepository.class);
        var voucher = voucherRepo.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));
    }

}
