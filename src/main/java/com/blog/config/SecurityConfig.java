package com.blog.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeHttpRequests((authorize) ->
                //authorize.anyRequest().authenticated()
                authorize.antMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .antMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
				);

	return http.build();
	}
	
	
	//since we are doing database authentication, in in memory authentication is not required
	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails piyush =
	 * User.builder().username("Piyush").password(passwordEncoder().encode(
	 * "Piyush@123")).roles("USER").build(); UserDetails admin =
	 * User.builder().username("admin").password(passwordEncoder().encode(
	 * "admin@123")).roles("ADMIN").build();
	 * 
	 * return new InMemoryUserDetailsManager(piyush, admin); }
	 */
	
}
