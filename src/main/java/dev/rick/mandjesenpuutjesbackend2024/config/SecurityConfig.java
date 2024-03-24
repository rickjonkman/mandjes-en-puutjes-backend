package dev.rick.mandjesenpuutjesbackend2024.config;

import dev.rick.mandjesenpuutjesbackend2024.jwt.JwtRequestFilter;
import dev.rick.mandjesenpuutjesbackend2024.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, CustomUserDetailsService userDetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder encoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder);
        return new ProviderManager(auth);
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

//                        PERMITTED TO ADMIN ONLY
                                .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasRole("ADMIN")

//                        PERMITTED TO AUTHENTICATED USER
                                .requestMatchers(HttpMethod.GET, "/api/v1/user/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/user/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/shopping-lists/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/v1/shopping-lists/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/shopping-lists/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/shopping-lists/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/recipes/auth/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/recipes/auth/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/recipes/add-new").authenticated()


//                        OPEN TO ALL
                                .requestMatchers(HttpMethod.POST, "/api/v1/products/open/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/products/open/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/users/authenticate").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/recipes/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/recipes/get-thumbnails").permitAll()

                                .anyRequest().denyAll()
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
