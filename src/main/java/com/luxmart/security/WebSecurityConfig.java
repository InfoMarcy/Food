package com.luxmart.security;



import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;





@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
       
              .antMatchers("/report").permitAll()
            .antMatchers("/register").permitAll()
            .antMatchers("/error").permitAll()
            .antMatchers("/forgot", "/reset").permitAll()
            .antMatchers("/about-us").permitAll()
            .antMatchers("/confirm").permitAll()
            .antMatchers("/confirm/**").permitAll()            
            .antMatchers("/").permitAll()
            .antMatchers("/index").permitAll()
            .antMatchers("/restaurants").permitAll() 
            .antMatchers("/restaurants/**").permitAll()                                 
            .antMatchers("/resources/**").permitAll()          
            .antMatchers("/rest/cart/**").permitAll()
           
            
            .anyRequest().fullyAuthenticated()
        	  .and()
        	    .formLogin()
        	    .loginPage("/login").permitAll()  
        	    .failureUrl("/login?error").permitAll()
        	    .defaultSuccessUrl("/")        	    
        	    .usernameParameter("email").passwordParameter("password")        	    
        	  .and()	  
        	    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index")
        	   .and()
        	   .exceptionHandling().accessDeniedPage("/403");
        
        http.csrf().disable();

    }
	
	
	
	  // connect to the JDBC dataSource
 	@Autowired
	 DataSource dataSource;
 	
 	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
 	


@Autowired
public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {			 

String userByEmailQuery = "Select email,password, enabled from users where email=?;";
String roleByEmailQuery = "SELECT email, authority FROM authorities WHERE email =?;";


auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder)
.usersByUsernameQuery(userByEmailQuery)
.authoritiesByUsernameQuery(roleByEmailQuery);



}
	
}
