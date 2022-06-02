package uz.pdp.online.lesson_2_2.Config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class XavsizlikSozlamasi extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("SuperAdmin").password(passwordEncoder().encode("123")).roles("SUPER ADMIN")
                .and()
                .withUser("Moderator").password(passwordEncoder().encode("456")).roles("MODERATOR")
                .and()
                .withUser("Operator").password(passwordEncoder().encode("789")).roles("OPERATOR");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/product*").hasAnyRole("SUPERADMIN","OPERATOR","MODERATOR")
//                .antMatchers(HttpMethod.PUT, "/api/product").hasAnyRole("SUPERADMIN","MODERATOR")
//                .antMatchers(HttpMethod.POST, "/api/product**").hasAnyRole("SUPERADMIN","MODERATOR")
//                .antMatchers("/api/product**").hasRole("SUPERADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
