//package kz.wonder.wonderuser.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
//import org.keycloak.OAuth2Constants;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class Beans {
//    @Value("${application.keycloak-url}")
//    private String keycloakUrl;
//
//    @Bean
//    Keycloak keycloak() {
//        return KeycloakBuilder.builder()
//                .serverUrl(keycloakUrl)
//                .realm("miniland")
//                .clientId("miniland")
//                .grantType(OAuth2Constants.PASSWORD)
//                .username("admin")
//                .password("admin_password")
//                .resteasyClient(new ResteasyClientBuilderImpl()
//                        .connectionPoolSize(10).build())
//                .build();
//    }
//}