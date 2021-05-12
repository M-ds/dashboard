package polar.bear.dashboard.person.profile

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import polar.bear.dashboard.person.profile.reply.JsonUserProfileReplyModel
import polar.bear.dashboard.person.profile.dto.UserProfileDto
import polar.bear.dashboard.person.usecase.UserProfileUseCase

@CrossOrigin(origins = ["http://localhost:1994"])
@RestController
@RequestMapping("/rest/user")
class UserProfileController(
    private val userProfileUseCase: UserProfileUseCase
) {

    @GetMapping("/{userId}/profile")
    fun getUserProfile(
        @PathVariable(value = "userId") userId: String
    ): JsonUserProfileReplyModel {
        val userProfile = userProfileUseCase.getUserProfile(userId)
        val userProfileDto = UserProfileDto.fromDomainModel(userProfile)
        return JsonUserProfileReplyModel(
            valid = true,
            error = null,
            model = userProfileDto
        )
    }
}