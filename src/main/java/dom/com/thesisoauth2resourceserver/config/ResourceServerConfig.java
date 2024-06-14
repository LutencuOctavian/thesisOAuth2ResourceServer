package dom.com.thesisoauth2resourceserver.config;

import dom.com.thesisoauth2resourceserver.config.conterters.CustomJwtGrantedAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.AUD;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ResourceServerConfig {

    private static final String RESOURCE_SERVER ="ResourceServer";
    @Bean
    public SecurityFilterChain securityFilterChainAs(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf-> csrf.ignoringRequestMatchers("category/new-category"))
                .authorizeHttpRequests(auth-> auth
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter())))
                .build();
    }

    @Bean
    public JwtAuthenticationConverter customJwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());
        return converter;
    }

    @Bean
    OAuth2TokenValidator<Jwt> audienceValidator() {
        return new JwtClaimValidator<List<String>>(AUD, aud -> aud.contains(RESOURCE_SERVER));
    }
}
