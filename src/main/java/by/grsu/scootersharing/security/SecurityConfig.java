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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
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
        http.cors().and().csrf().disable();
//        http.cors().configurationSource(request -> null);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/scooter-sharing/api/login/**", "/scooter-sharing/api/token/refresh/**", "/scooter-sharing/api/user/create/**").permitAll();

        http.authorizeRequests().antMatchers(GET, "/scooter-sharing/api/scooters/**").hasAuthority("User");
        http.authorizeRequests().antMatchers(POST,"/scooter-sharing/api/scooters/create/**").hasAuthority("Admin");
        http.authorizeRequests().antMatchers(PUT, "/scooter-sharing/api/scooters/update/**").hasAuthority("User");
        http.authorizeRequests().antMatchers(DELETE,"/scooter-sharing/api/scooters/delete/**").hasAuthority("Admin");

        http.authorizeRequests().antMatchers(GET, "/scooter-sharing/api/user/**").hasAuthority("User");
        http.authorizeRequests().antMatchers(PUT, "/scooter-sharing/api/user/update/**").hasAuthority("User");
        http.authorizeRequests().antMatchers(DELETE, "/scooter-sharing/api/user/delete/**").hasAuthority("Admin");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
