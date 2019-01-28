package cz.uhk.ppro.project.springSecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

        //menu (creating) rights
        http.authorizeRequests().antMatchers("/addWorker").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/addHall").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/addWorkplace").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/addDocument")
                .access("hasAnyRole('ROLE_Admin', 'ROLE_Master')");

        //editing rights
        http.authorizeRequests().antMatchers("/worker/edit/*").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/hall/edit/*").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/workplace/edit/*").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/document/edit/*")
                .access("hasAnyRole('ROLE_Admin', 'ROLE_Master')");

        //deleting rights
        http.authorizeRequests().antMatchers("/worker/edit/*").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/deleteHall/*").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/hall/*/deleteWorkplace/*")
                .access("hasRole('ROLE_Admin')");
        http.authorizeRequests().antMatchers("/deleteDocument/*").access("hasRole('ROLE_Admin')");

        // user doesn't have access - wrong role
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/loginFailed")
                .usernameParameter("login")
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

}