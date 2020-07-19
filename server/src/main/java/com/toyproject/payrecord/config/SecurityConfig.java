package com.toyproject.payrecord.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.toyproject.payrecord.security.jwt.JwtTokenFilterConfigurer;
import com.toyproject.payrecord.security.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .disable()
//                .csrf()
//                .disable()
//                .formLogin()
//                .disable()
//                .headers().frameOptions().disable();
//    }

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	// 구현하기
//	@Autowired
//	private CustomAuthenticationSuccessHandler successHandler;
//	
//	@Autowired
//	private CustomAuthenticationFailureHandler failureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 0718 comment
//		http.authorizeRequests().antMatchers("/h2-console/**", "/signup", "/login", "/api/**").permitAll().and().csrf()
//				.disable().headers().frameOptions().disable().and().authorizeRequests().anyRequest().authenticated()
//				.and().formLogin().disable().
//				userDetailsService(userDetailsService);

		//

		// Entry points
		http.authorizeRequests()//
				.antMatchers("/api/v1/signin").permitAll()//
				.antMatchers("/api/v1/signup").permitAll()//
				.antMatchers("/h2-console/**").permitAll().and().csrf().disable().headers().frameOptions().disable()
				.and().authorizeRequests()
				// Disallow everything else..
				.anyRequest().authenticated();

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

		// sessionManagement not used. => use jwt
		http.sessionManagement().disable();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security/**", "/swagger-ui.html", "/webjars/**", "/swagger/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
