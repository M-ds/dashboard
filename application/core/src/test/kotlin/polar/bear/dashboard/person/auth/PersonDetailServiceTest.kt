package polar.bear.dashboard.person.auth

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.Role
import polar.bear.dashboard.repository.user.MockPersonRepository
import polar.bear.dashboard.utils.Marker.GIVEN
import polar.bear.dashboard.utils.Marker.THEN
import polar.bear.dashboard.utils.Marker.WHEN

internal class PersonDetailServiceTest {
    private val mockPersonRepository = MockPersonRepository()

    private val underTest = PersonDetailService(mockPersonRepository)

    private val validUsername = "Miebels"
    private val emptyUsername = ""

    private val personDetail = PersonDetail(validUsername, "randomPassword", true, mutableListOf(Role.ROLE_USER, Role.ROLE_MEMBER))
    private val emptyPersonDetail = PersonDetail("", "", false, mutableListOf())

    @Test
    fun `Happy flow of PersonDetailService`(){
        GIVEN
        mockPersonRepository.loadUser(personDetail)
        val personAuthorities = personDetail.roles.map { role -> SimpleGrantedAuthority(role.name) }

        WHEN
        val result = underTest.loadUserByUsername(validUsername)

        THEN
        assert(result.username == personDetail.username)
        assert(result.password == personDetail.password)
        assert(result.isEnabled == personDetail.isActive)
        assert(result.authorities.containsAll(personAuthorities))
    }

    @Test
    fun `Exception is thrown because user is empty`(){
        GIVEN
        mockPersonRepository.loadUser(emptyPersonDetail)

        WHEN
        assertThrows<UsernameNotFoundException> {
            underTest.loadUserByUsername(emptyUsername)
        }
    }

    @Test
    fun `Exception is thrown because username is null`(){
        GIVEN
        mockPersonRepository.loadUser(null)

        WHEN
        assertThrows<UsernameNotFoundException> {
            underTest.loadUserByUsername(emptyUsername)
        }
    }
}
