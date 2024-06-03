package com.strawhats.RecruitmentPortal.config;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.strawhats.RecruitmentPortal.security.CustomerDetailsService;
import com.strawhats.RecruitmentPortal.security.JwtAuthEntryPoint;

@EnableWebSecurity
@Configuration
public class SecurtiyConfig {

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//            .cors()
//            .and()
//            .authorizeRequests(authorize -> authorize.anyRequest().permitAll());
//        return http.build();
//    }
	private CustomerDetailsService customerDetailsService;
    private JwtAuthEntryPoint authEntryPoint;

    @Autowired
    public SecurtiyConfig(CustomerDetailsService customerDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.customerDetailsService = customerDetailsService;
        this.authEntryPoint = authEntryPoint;
    }
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.out.println("in the security filter chain bean");

        http
                .csrf(csrf -> csrf.disable())
                .cors().and()
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authEntryPoint))
//                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(httpBasic -> httpBasic.realmName("Recruitment").authenticationEntryPoint(authEntryPoint))
                .logout(logout -> logout.permitAll());
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
