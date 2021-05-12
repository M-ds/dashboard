package polar.bear.dashboard.person.infra

import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.PersonProfile
import java.util.*

interface PersonRepository {

    /**
     * Obtain all the security related information for a specific user.
     * All roles are gathered, and stored in the security context.
     */
    fun loadUserByUsername(username: String): Optional<PersonDetail>

    fun getPersonProfile(personId: Int): Optional<PersonProfile>

}