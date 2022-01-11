package com.example.demo.securityConfiguration;

import com.example.demo.domain.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled "
                        + "from users "
                        + "where username LIKE ?")
                .authoritiesByUsernameQuery("select username,authority "
                        + "from authorities "
                        + "where username LIKE ?");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .formLogin().and().authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin").hasRole(AppUserRole.ADMIN.name())
                    .antMatchers("/user").hasRole(AppUserRole.USER.name())
                    //.antMatchers(HttpMethod.POST,"/management/api/user").hasAuthority("ROLE_" + AppUserRole.ADMIN.name())
                    //.antMatchers(HttpMethod.DELETE,"/management/api/users/**").hasAuthority("ROLE_" + AppUserRole.ADMIN.name())
                    //.antMatchers(HttpMethod.GET,"/management/api/users").hasAuthority("ROLE_" + AppUserRole.USER.name())
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll().and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll().and()
                    .exceptionHandling().accessDeniedPage("/404");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("User1").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("Admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
