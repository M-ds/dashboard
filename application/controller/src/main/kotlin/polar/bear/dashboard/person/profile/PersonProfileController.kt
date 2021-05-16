package polar.bear.dashboard.person.profile

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.person.profile.dto.UserProfileDto
import polar.bear.dashboard.person.usecase.PersonProfileUseCase

@CrossOrigin(origins = ["http://localhost:1994"])
@RestController
class PersonProfileController(
    private val personProfileUseCase: PersonProfileUseCase
) {

    @GetMapping("/api/person/{personId}/profile")
    fun getPersonProfile(
        @PathVariable(value = "personId") userId: String
    ): UserProfileDto {
        val userProfile = personProfileUseCase.getPersonProfile(userId)
        return UserProfileDto.fromDomainModel(userProfile)
    }
}