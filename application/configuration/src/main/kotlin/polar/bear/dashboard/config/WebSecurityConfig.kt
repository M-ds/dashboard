package polar.bear.dashboard.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import polar.bear.dashboard.person.auth.MyUserDetailsService
import polar.bear.dashboard.person.domain.SecurityRole
import polar.bear.dashboard.security.JwtAuthEntryPoint
import polar.bear.dashboard.security.JwtRequestFilter

@Configuration
@EnableWebSecurity
open class WebSecurityConfig(
    private val jwtAuthEntryPoint: JwtAuthEntryPoint,
    private val jwtRequestFilter: JwtRequestFilter,
    private val myUserDetailsService: MyUserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        super.configure(auth)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            // Make sure we use stateless session: sessions wont' be used to store user's state.
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // We don't need authentication for these patterns
            // AKA public endpoints
            .authorizeRequests()
            .antMatchers(
                "/user/auth/_log-in",
                "/user/auth/_signup"
            ).permitAll()
            // Private endpoints, which need a specific role. (this is better than via Controller)
            .antMatchers("/api/user/**")
            .hasAnyRole(SecurityRole.USER.name, SecurityRole.MEMBER.name, SecurityRole.ADMIN.name)
            .antMatchers("/api/marketplace")
            .hasAnyRole(SecurityRole.USER.name, SecurityRole.MEMBER.name, SecurityRole.ADMIN.name)
            .anyRequest()
            .authenticated()

        // Add a filter to validate the tokens with every request
        http.addFilterBefore(
            jwtRequestFilter,
            UsernamePasswordAuthenticationFilter::class.java
        )
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}