package PollardCreations.Tjabal;

import PollardCreations.Tjabal.Services.StudentDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author james
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
    {
        return http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests
                    (auth -> auth
                        .requestMatchers("/style.css").permitAll()
                        .requestMatchers("/signInPage").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                    )
                .formLogin(form -> form
                        .loginPage("/signInPage").permitAll()
//                        .loginProcessingUrl("/SignInPage")
                        .defaultSuccessUrl("/signedInStudentAddSession", true)
                        .permitAll()
//                        .failureUrl("/SignInPage?error=true")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/SignInPage?logout")
                )
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider
        (StudentDetailsService userDetailsService, PasswordEncoder passwordEncoder) 
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
