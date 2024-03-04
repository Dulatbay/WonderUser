package kz.wonder.wonderuser.security;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.auth.converter.resource-id}")
    private String clientId;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                )
                .authorizeHttpRequests((auth) -> {
                    auth
                            .anyRequest()
                            .permitAll();
                })
        ;

        http
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(new CustomAccessDeniedHandler());
                }
        );

        http.addFilterBefore(new CustomCorsFilter(), ChannelProcessingFilter.class);

        return http.build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            Object client = resourceAccess.get(clientId);

            if (client == null)
                return List.of();


            @SuppressWarnings("unchecked")
            LinkedTreeMap<String, List<String>> clientRoleMap = (LinkedTreeMap<String, List<String>>) client;

            List<String> clientRoles = new ArrayList<>(clientRoleMap.get("roles"));


            return clientRoles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}
