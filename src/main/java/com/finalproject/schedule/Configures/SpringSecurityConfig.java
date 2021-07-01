package com.finalproject.schedule.Configures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableAutoConfiguration
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    private final DataSource dataSource;

    public SpringSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select email,password,enabled from user_tbl where email=?")
                .authoritiesByUsernameQuery("select email,roles from authorities where email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/loginrole", "/style.css", "/index.js", "/css/**", "/js/**", "/assets/**", "/fontawesome-free/**")
                .permitAll()
                .antMatchers("/Users/**", "/Courses/**", "/Days/**", "/Bells/**", "/timetabelbell/**" , "/admin_main",
                        "/api/Users/**", "/api/Courses/**","/api/Days/**","/api/Bells/**","/api/timetabelbell/**")
                .hasAuthority("ADMIN")
                .antMatchers("/master_main/**", "/master_course/**", "/master_timetable/**",
                        "/api/MasterCourse/**")
                .hasAuthority("MASTER")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").usernameParameter("email")
                .permitAll().and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

}
