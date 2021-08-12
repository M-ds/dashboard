package polar.bear.dashboard.person.infra

import java.util.UUID
import polar.bear.dashboard.person.auth.domain.Person
import polar.bear.dashboard.person.auth.domain.Role
import polar.bear.dashboard.person.domain.TokenId
import polar.bear.dashboard.person.verifytoken.domain.PersonRegisteredSuccess

interface PersonRepository {
    fun usernameExists(username: String): Boolean
    fun emailExits(email: String): Boolean

    fun getUsernameAndEmail(tokenId: TokenId): UsernameAndEmailResponse
    data class UsernameAndEmailResponse(val username: String, val email: String)

    fun getPersonIdFromUsername(username: String): UUID
    fun save(person: Person): Boolean
    fun getRoleId(role: Role): UUID
    fun successfulRegistered(personRegistered: PersonRegisteredSuccess)
}