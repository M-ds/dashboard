package polar.bear.dashboard.repository.auth

import java.util.Optional
import polar.bear.dashboard.person.auth.domain.PersonDetail
import polar.bear.dashboard.person.auth.infra.AuthenticationRepository

class MockAuthenticationRepository: AuthenticationRepository {

    private var personDetail: Optional<PersonDetail> = Optional.empty()

    override fun loadUserByUsername(username: String): Optional<PersonDetail> {
        return this.personDetail
    }

    fun loadUser(personDetail: PersonDetail?) {
        if (personDetail == null || personDetail.roles.isEmpty()) {
            this.personDetail = Optional.empty()
        } else {
            this.personDetail = Optional.of(personDetail)
        }
    }
}