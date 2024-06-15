package dom.com.thesisoauth2resourceserver.config;

import dom.com.thesisoauth2resourceserver.config.converters.CustomJwtGrantedAuthoritiesConverter;
import dom.com.thesisoauth2resourceserver.converters.GenericConverterType;
import dom.com.thesisoauth2resourceserver.dto.ImageDTO;
import dom.com.thesisoauth2resourceserver.enties.ImageEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames.AUD;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ResourceServerConfig {

    private static final String RESOURCE_SERVER ="ResourceServer";
    @Bean
    public SecurityFilterChain securityFilterChainAs(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf-> csrf.ignoringRequestMatchers("category/new-category", "image/new-image/**"))
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

    @Bean
    public GenericConverterType genericConverterType(@Qualifier("converterImageEntity2ImageDTO") Converter<ImageEntity, ImageDTO> converterImageEntity2ImageDTO,
                                                     @Qualifier("converterImageDTO2ImageEntity") Converter<ImageDTO, ImageEntity> converterImageDTO2ImageEntity){
        Map<Class<?>, Converter<?, ?>> mapOfClassConverterAndItsImplementation = new HashMap<>();
        mapOfClassConverterAndItsImplementation.put(ImageDTO.class, converterImageEntity2ImageDTO);
        mapOfClassConverterAndItsImplementation.put(ImageEntity.class, converterImageDTO2ImageEntity);
        return new GenericConverterType(mapOfClassConverterAndItsImplementation);
    }
}
