package com.tese.webplatform.iposcore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tese.webplatform.iposcore.security.services.*;
import com.tese.webplatform.iposcore.security.jwt.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
 
    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
 
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }
 
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
 
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //adicionar os restantes 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/surgeryDataInfo/**").permitAll()
                .antMatchers("/ACSRiskCalculatorDataInfo/**").permitAll()
                .antMatchers("/ARISCATDataInfo/**").permitAll()
                .antMatchers("/hospitalDischargeDataInfo/**").permitAll()
                .antMatchers("/internmentCaracteristicsInfo/**").permitAll()
                .antMatchers("/patientsInfo/**").permitAll()
                .antMatchers("/postSurgicalComplicationsDataInfo/**").permitAll()
                .antMatchers("/ppossumDataInfo/**").permitAll()
                .antMatchers("/preoperativeComorbiditiesDataInfo/**").permitAll()
                .antMatchers("/applicationForUciInfo/**").permitAll()
                .antMatchers("/listPageable**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/CharlsonDataInfo/**").permitAll()
                .antMatchers("/listForDataExploration/**").permitAll()
                .antMatchers("/listForFeatureRanking/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}