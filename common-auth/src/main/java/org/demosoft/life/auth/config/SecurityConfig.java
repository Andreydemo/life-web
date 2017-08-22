package org.demosoft.life.auth.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demosoft.life.auth.CommonAuthenticationProvider;
import org.demosoft.life.auth.filter.AuthenticationTokenFilter;
import org.demosoft.life.auth.filter.CommonAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Log logger = LogFactory.getLog(WebSecurityConfigurerAdapter.class);

    private final CommonAuthenticationEntryPoint authenticationEntryPoint;
    private final CommonAuthenticationProvider authenticationProvider;

    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }


    public SecurityConfig(CommonAuthenticationEntryPoint authenticationEntryPoint, CommonAuthenticationProvider authenticationProvider) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/**/*.html", "/**/*.css", "/**/*.ttf", "/**/*.js", "/**.gif",
                        "/swagger/**", "/swagger-resources/**", "/v2/**", "/webjars/**", "/favicon" + ".ico", "/api/**")
                .permitAll().antMatchers(HttpMethod.POST,"/auth/*").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().cacheControl();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new AuthenticationTokenFilter();
    }
}
