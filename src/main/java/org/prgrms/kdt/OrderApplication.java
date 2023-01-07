package org.prgrms.kdt;

import org.prgrms.kdt.order.OrderProperties;
import org.prgrms.kdt.voucher.FixedAmountVoucher;
import org.prgrms.kdt.voucher.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.MessageFormat;
import java.util.UUID;

@SpringBootApplication
@ComponentScan(basePackages = {"org.prgrms.kdt.order", "org.prgrms.kdt.voucher"})
public class OrderApplication {
    private static final Logger logger = LoggerFactory.getLogger(OrderApplication.class);

    public static void main(String[] args) {


        var applicationContext = SpringApplication.run(OrderApplication.class, args);
        var orderProperties = applicationContext.getBean(OrderProperties.class);
        logger.warn("version -> {0}", orderProperties.getVersion());
        logger.warn("minimumOrderAmount -> {0}", orderProperties.getMinimumOrderAmount());
        logger.warn("supportVendors -> {0}", orderProperties.getDescription());

    }

}
