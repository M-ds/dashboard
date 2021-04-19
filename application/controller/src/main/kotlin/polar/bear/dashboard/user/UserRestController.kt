package polar.bear.dashboard.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.common.reply.user.JsonUserReply
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
    ): JsonUserReply {
        val userProfile = userProfileUseCase.getUserProfile(userId)
        val userProfileDto = UserProfileDto.fromDomainModel(userProfile)
        return JsonUserReply(
            valid = true,
            error = null,
            model = userProfileDto
        )
    }

}