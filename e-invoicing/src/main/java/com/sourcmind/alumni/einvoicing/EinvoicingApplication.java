package com.sourcmind.alumni.einvoicing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EinvoicingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EinvoicingApplication.class, args);
    }

}
