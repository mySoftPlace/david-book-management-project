package canoo.app.proj.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

/**
 * 
 * @author David Sossavi
 * 
 *         To configurate the login to the application for the simple test i
 *         save the users simply in memory I will improve the implementation of
 *         this class by calling users saved in User table for the login
 *
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder users = User.withDefaultPasswordEncoder();
		User user = (User) users.username("user").password("5678").roles("USER").build();
		User admin = (User) users.username("admin").password("1234").roles("USER", "ADMIN").build();
		auth.inMemoryAuthentication().withUser(user);
		auth.inMemoryAuthentication().withUser(admin);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().defaultSuccessUrl("/user/book_index", true);
		http.authorizeRequests().antMatchers("/user/**").hasRole("USER");
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
	}
}
