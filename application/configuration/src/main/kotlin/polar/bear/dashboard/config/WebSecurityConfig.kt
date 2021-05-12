package polar.bear.dashboard.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import polar.bear.dashboard.security.JwtAuthEntryPoint

@Configuration
@EnableWebSecurity
open class WebSecurityConfig(
    private val jwtAuthEntryPoint: JwtAuthEntryPoint,
    private val jwtRequestFilter: JwtRequestFilter,
    private val myUserDetailsService: MyUserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().antMatchers("/")
            .permitAll()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService? {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }
}