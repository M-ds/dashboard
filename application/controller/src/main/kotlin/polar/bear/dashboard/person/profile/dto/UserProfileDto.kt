package polar.bear.dashboard.person.profile.dto

import polar.bear.dashboard.person.domain.PersonProfile

data class UserProfileDto(
    val userName: String,
    val password: String,
    val email: String
){
    companion object {

        fun fromDomainModel(
            personProfile: PersonProfile
        ): UserProfileDto{
            return UserProfileDto(
                userName = personProfile.userName,
                password = personProfile.password,
                email = personProfile.email
            )
        }
    }
}
