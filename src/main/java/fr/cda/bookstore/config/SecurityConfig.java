package fr.cda.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private ApplicationContext applicationContext;

    // Récupération du secret dans les properties
    @Value("${secret}")
    private String secret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests(custom -> {
                    try {
                        custom.requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers( HttpMethod.GET,
                                        "/v2/api-docs",
                                        "/swagger-resources/**",
                                        "/swagger-ui.html**",
                                        "/webjars/**",
                                        "favicon.ico",
                                        "/**"
                                        ).permitAll()
                                .anyRequest().authenticated()
                                .and().addFilterBefore(new JwtExceptionHandlerFilter(), JWTAuthorizationFilter.class)
                                .addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager(), secret));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                );

        return http.build();
    }

    // Permet d'ignorer la sécurité sur certaines routes
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/api/security/resetPassword", "/login");
    }

    // Permet de créer un BCrypt Password Encoder et de définir le niveau de hashage
    // 10 correspond au nombre de passage de l'algorithme de hashage
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    // Intégration de la librairie Bcrypt pour la vérification du mot de passe envoyé avec celui stocké en base hashé
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, SecurityUserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // Ajout du Filter d'authentification pour la route /login
    @Bean
    public FilterRegistrationBean<JWTAuthenticationFilter> loggingFilter() throws Exception {
        FilterRegistrationBean<JWTAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), secret, applicationContext));
        registrationBean.addUrlPatterns("/login");
        return registrationBean;
    }
}
