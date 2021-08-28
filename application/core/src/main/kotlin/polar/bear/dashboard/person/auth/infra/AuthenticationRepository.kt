package polar.bear.dashboard.person.auth.infra

import java.util.Optional
import polar.bear.dashboard.person.auth.domain.PersonDetail

interface AuthenticationRepository {
    /**
     * Obtain all the security related information for a specific user.
     * All roles are gathered, and stored in the security context.
     */
    fun loadUserByUsername(username: String): Optional<PersonDetail>
}