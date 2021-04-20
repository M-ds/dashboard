package polar.bear.dashboard.user.dto

import polar.bear.dashboard.user.domain.UserProfile

data class UserProfileDto(
    val userName: String,
    val password: String,
    val email: String
){
    companion object {

        fun fromDomainModel(
            userProfile: UserProfile
        ): UserProfileDto{
            return UserProfileDto(
                userName = userProfile.userName,
                password = userProfile.password,
                email = userProfile.email
            )
        }
    }
}
