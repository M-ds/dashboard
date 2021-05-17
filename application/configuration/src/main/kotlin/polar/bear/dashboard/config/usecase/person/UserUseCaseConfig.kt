package polar.bear.dashboard.config.usecase.person

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import polar.bear.dashboard.person.impl.SignInUseCaseImpl
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.usecase.PersonProfileUseCase
import polar.bear.dashboard.person.impl.PersonProfileUseCaseImpl
import polar.bear.dashboard.person.usecase.SignInUseCase
import polar.bear.dashboard.util.jwt.JwtUtil

@Configuration
open class UserUseCaseConfig {

    @Bean
    open fun initUserProfileUseCase(
        personRepository: PersonRepository
    ): PersonProfileUseCase {
        return PersonProfileUseCaseImpl(
            personRepository
        )
    }

    @Bean
    open fun initSignInUseCase(
        authenticationManager: AuthenticationManager,
        jwtUtil: JwtUtil,
        personRepository: PersonRepository
    ): SignInUseCase {
        return SignInUseCaseImpl(
            authenticationManager = authenticationManager,
            jwtUtil = jwtUtil,
            personRepository = personRepository
        )
    }

}