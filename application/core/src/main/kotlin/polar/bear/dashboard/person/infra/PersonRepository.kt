package polar.bear.dashboard.person.infra

import polar.bear.dashboard.person.auth.domain.Person
import polar.bear.dashboard.person.auth.domain.PersonDetail
import polar.bear.dashboard.person.auth.domain.Role
import java.util.Optional
import java.util.UUID
import polar.bear.dashboard.person.verifytoken.domain.PersonRegisteredSuccess

interface PersonRepository {

    /**
     * Obtain all the security related information for a specific user.
     * All roles are gathered, and stored in the security context.
     */
    fun loadUserByUsername(username: String): Optional<PersonDetail>

    fun usernameExists(username: String): Boolean

    fun emailExits(email: String): Boolean

    fun getPersonIdFromUsername(username: String): UUID

    fun save(person: Person): Boolean

    fun getRoleId(role: Role): UUID

    fun successfulRegistered(personRegistered: PersonRegisteredSuccess)
}