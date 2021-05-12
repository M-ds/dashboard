package polar.bear.dashboard.person

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import polar.bear.dashboard.common.reply.user.JsonUserReplyModel
import polar.bear.dashboard.person.dto.UserProfileDto
import polar.bear.dashboard.person.usecase.UserProfileUseCase

@CrossOrigin(origins = ["http://localhost:1994"])
@RestController
@RequestMapping("/rest/user")
class UserRestController(
    private val userProfileUseCase: UserProfileUseCase
) {

    @GetMapping("/{userId}/profile")
    fun getUserProfile(
        @PathVariable(value = "userId") userId: String
    ): JsonUserReplyModel {
        val userProfile = userProfileUseCase.getUserProfile(userId)
        val userProfileDto = UserProfileDto.fromDomainModel(userProfile)
        return JsonUserReplyModel(
            valid = true,
            error = null,
            model = userProfileDto
        )
    }
}