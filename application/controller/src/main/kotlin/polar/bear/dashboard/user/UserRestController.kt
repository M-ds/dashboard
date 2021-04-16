package polar.bear.dashboard.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.user.dto.UserProfileDto
import polar.bear.dashboard.user.usecase.UserProfileUseCase

@RestController
@RequestMapping("/rest/user")
class UserRestController(
    private val userProfileUseCase: UserProfileUseCase
) {

    @GetMapping("/{userId}/profile")
    fun getUserProfile(
        @PathVariable(value = "userId") userId: String
    ): UserProfileDto {
        val userProfile = userProfileUseCase.getUserProfile(userId)
        return UserProfileDto.fromDomainModel(userProfile)
    }

}