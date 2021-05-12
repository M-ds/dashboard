package polar.bear.dashboard.config.usecase.person

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import polar.bear.dashboard.person.impl.SignInUseCaseImpl
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.usecase.UserProfileUseCase
import polar.bear.dashboard.person.impl.UserProfileUseCaseImpl
import polar.bear.dashboard.person.usecase.SignInUseCase

@Configuration
open class UserUseCaseConfig {

    @Bean
    open fun initUserProfileUseCase(
        personRepository: PersonRepository
    ): UserProfileUseCase {
        return UserProfileUseCaseImpl(
            personRepository
        )
    }

    @Bean
    open fun initSignInUseCase(
        personRepository: PersonRepository
    ): SignInUseCase {
        return SignInUseCaseImpl(
            personRepository
        )
    }

}