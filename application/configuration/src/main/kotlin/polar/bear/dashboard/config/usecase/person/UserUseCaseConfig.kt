package polar.bear.dashboard.config.usecase.person

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import polar.bear.dashboard.person.profile.usecase.PersonProfileUseCaseImpl
import polar.bear.dashboard.person.signin.usecase.impl.SignInUseCaseImpl
import polar.bear.dashboard.person.signup.usecase.impl.SignupUseCaseImpl
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.profile.infra.PersonProfileRepository
import polar.bear.dashboard.person.signup.infra.SendEmail
import polar.bear.dashboard.person.signup.infra.TokenRepository
import polar.bear.dashboard.person.profile.usecase.PersonProfileUseCase
import polar.bear.dashboard.person.signin.usecase.SignInUseCase
import polar.bear.dashboard.person.signup.usecase.SignupUseCase
import polar.bear.dashboard.person.verifytoken.infra.RetrieveTokenRepository
import polar.bear.dashboard.person.verifytoken.usecase.VerifyTokenUseCase
import polar.bear.dashboard.person.verifytoken.usecase.impl.VerifyTokenUseCaseImpl
import polar.bear.dashboard.util.jwt.JwtUtil

@Configuration
open class UserUseCaseConfig {

    @Bean
    open fun initUserProfileUseCase(
        personProfileRepository: PersonProfileRepository
    ): PersonProfileUseCase {
        return PersonProfileUseCaseImpl(
            personProfileRepository
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

    @Bean
    open fun initSignupUseCase(
        personRepository: PersonRepository,
        tokenRepository: TokenRepository,
        sendEmail: SendEmail,
        passwordEncoder: PasswordEncoder
    ): SignupUseCase {
        return SignupUseCaseImpl(
            personRepository = personRepository,
            tokenRepository = tokenRepository,
            sendEmail = sendEmail,
            passwordEncoder = passwordEncoder
        )
    }

    @Bean
    open fun initVerifyTokenRepositoryUseCase(
        retrieveTokenRepository: RetrieveTokenRepository,
        personRepository: PersonRepository
    ): VerifyTokenUseCase {
        return VerifyTokenUseCaseImpl(
            retrieveTokenRepository = retrieveTokenRepository,
            personRepository = personRepository
        )
    }

}