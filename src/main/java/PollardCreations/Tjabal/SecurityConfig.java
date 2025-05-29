package PollardCreations.Tjabal;

import PollardCreations.Tjabal.Services.StudentDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
    {
        return http.authorizeHttpRequests
                (auth -> auth.requestMatchers("/SignInPage", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/SignInPage")
                        .defaultSuccessUrl("/SignedInStudentAddSession", true)
                        .permitAll()
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
