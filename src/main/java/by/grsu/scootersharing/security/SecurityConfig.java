package by.grsu.scootersharing.security;

import by.grsu.scootersharing.filter.CustomAuthenticationFilter;
import by.grsu.scootersharing.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/scooter-sharing/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //delete this region in future
        http.authorizeRequests().anyRequest().permitAll();

//        http.authorizeRequests().antMatchers("/login").permitAll();

//        http.authorizeRequests().antMatchers(GET, "scooter-sharing/api/scooters/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(POST,"scooter-sharing/api/scooters/create/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(PUT, "scooter-sharing/api/scooters/update/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(DELETE,"scooter-sharing/api/scooters/delete/**").hasAnyAuthority("ROLE_ADMIN");
//
//        http.authorizeRequests().antMatchers(GET, "scooter-sharing/api/user/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(POST, "scooter-sharing/api/user/create/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(PUT, "scooter-sharing/api/user/update/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(DELETE, "scooter-sharing/api/user/delete/**").hasAnyAuthority("ROLE_ADMIN");

//        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
