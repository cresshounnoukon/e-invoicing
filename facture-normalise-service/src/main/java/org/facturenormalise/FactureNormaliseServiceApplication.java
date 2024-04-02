package org.facturenormalise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FactureNormaliseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FactureNormaliseServiceApplication.class, args);
    }

}
