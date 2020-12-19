package com.management.managementsystem.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    /*@Autowired
    private EmployeService employeService;*/

    @Autowired
   private AuthenticationFilter authenticationFilter;


     protected static final String[] AUTH_WHITELIST = {
             "/EmployeeManagementSystem/v2/api-docs",
             "/EmployeeManagementSystem/swagger/resources",
             "/EmployeeManagementSystem/swagger/resources/**",
             "/EmployeeManagementSystem/configuration/ui",
             "/EmployeeManagementSystem/configuration/security",
             "/EmployeeManagementSystem/swagger-ui.html",
             "/EmployeeManagementSystem/swagger.json",
             "/EmployeeManagementSystem/web0jars/**",
             "/EmployeeManagementSystem/webjars/**",
             "/login",
             "/EmployeeManagementSystem/authenticate",

     };
/*

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();

    }
*/


    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf()
                .disable()
                .headers().frameOptions().deny()
                .cacheControl().disable()
                .httpStrictTransportSecurity().and().xssProtection().block(false);
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity web)
    {
       web.ignoring().antMatchers(AUTH_WHITELIST);
    }
}
