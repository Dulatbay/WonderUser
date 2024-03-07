package kz.wonder.wonderuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WonderUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(WonderUserApplication.class, args);
    }

}
