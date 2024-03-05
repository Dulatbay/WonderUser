package kz.wonder.wonderuser.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "kz.wonder.wonderuser")
public class ApplicationConfig {
}
