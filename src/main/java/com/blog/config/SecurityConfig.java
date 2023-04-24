package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

	//@Autowired
	//private CustomUserDetailsService userDetailsService;

	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests((authorize) -> 
				//authorize.anyRequest().authenticated()    //authorize all the request for the all the user
		authorize.antMatchers(HttpMethod.GET, "/api/**").permitAll()
				).httpBasic(Customizer.withDefaults());

	return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails piyush = User.builder().username("Piyush").password(passwordEncoder().encode("Piyush@123")).roles("USER").build();
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin@123")).roles("ADMIN").build();

		return new InMemoryUserDetailsManager(piyush, admin);
	}
	
	
	
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET,
	 * "/api/**").permitAll()
	 * .antMatchers("/api/auth/**").permitAll().anyRequest().authenticated().and().
	 * httpBasic(); }
	 * 
	 * // @Bean // @Override // protected UserDetailsService userDetailsService() {
	 * // UserDetails piyush =
	 * User.builder().username("piyush").password(passwordEncoder().encode("pass")).
	 * roles("USER") // .build(); // UserDetails admin =
	 * User.builder().username("admin").password(passwordEncoder().encode("admin")).
	 * roles("ADMIN") // .build(); // // return new
	 * InMemoryUserDetailsManager(piyush, admin); // }
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
	 * ); }
	 * 
	 * @Override
	 * 
	 * @Bean public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 */

}
