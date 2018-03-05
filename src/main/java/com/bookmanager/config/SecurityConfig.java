package com.bookmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.bookmanager.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	UserService userService() {
		return new UserService();
	}
	
	@Bean
	BookSuccessHandler bookSuccessHandler(){
		return new BookSuccessHandler();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
		auth.userDetailsService(userService());
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http.authorizeRequests()
		.antMatchers("/bower_components/**", "/dist/**", "/plugins/**", "/documentation/**", "/pages/**", "/html/**").permitAll()  // 允许访问资源
		.antMatchers("/register").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.failureUrl("/login?error=true").defaultSuccessUrl("/bookList").permitAll()
		.and()
		.logout().permitAll();
		
//		http.authorizeRequests()
//        .anyRequest().authenticated()
//        .and().formLogin().loginPage("/login")
//        //设置默认登录成功跳转页面
//        .defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
//        .and()
//        //开启cookie保存用户数据
//        .rememberMe()
//        //设置cookie有效期
//        .tokenValiditySeconds(60 * 60 * 24 * 7)
//        //设置cookie的私钥
//        .key("")
//        .and()
//        .logout()
//        //默认注销行为为logout，可以通过下面的方式来修改
//        .logoutUrl("/custom-logout")
//        //设置注销成功后跳转页面，默认是跳转到登录页面
//        .logoutSuccessUrl("")
//        .permitAll();
		
	}
}
