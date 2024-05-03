package com.amadon.rtvagdshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Profile( "no-security" )
public class SecurityDisabledConfiguration
{
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    {
        return webSecurity ->
        {
            webSecurity.ignoring()
                    .requestMatchers( new AntPathRequestMatcher( "/**" ) );
        };
    }
}
