package polar.bear.dashboard.config.usecase.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import polar.bear.dashboard.user.infra.UserRepository
import polar.bear.dashboard.user.usecase.UserProfileUseCase
import polar.bear.dashboard.user.usecase.impl.UserProfileUseCaseImpl

@Configuration
open class UserUseCaseConfig {

    @Bean
    open fun initUserProfileUseCase(
        userRepository: UserRepository
    ): UserProfileUseCase {
        return UserProfileUseCaseImpl(
            userRepository
        )
    }

}