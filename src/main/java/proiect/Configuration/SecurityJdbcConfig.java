package proiect.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Profile("mysql | oracle")
public class SecurityJdbcConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/client//adresa/new").hasRole("ADMIN")
                .antMatchers("/bilet/client/*/verifica_discount").hasRole("GUEST")
                .antMatchers("/zbor/avion/new").hasRole("ADMIN")
                .antMatchers("/zbor/aeroport/new").hasRole("ADMIN")
                .antMatchers("/zbor/pilot/new").hasRole("ADMIN")
                .antMatchers("/zbor/destinatie/new").hasRole("ADMIN")
                .antMatchers("/zbor/delay/new").hasRole("ADMIN")
                .antMatchers("/zbor/new").hasRole("ADMIN").and()
                .formLogin().loginPage("/showLogInForm")
                .loginProcessingUrl("/authUser")
                .failureUrl("/login-error").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

    }


}

