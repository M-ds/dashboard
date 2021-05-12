package polar.bear.dashboard.person.auth

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import polar.bear.dashboard.person.infra.PersonRepository

/**
 * This class is the custom implementation of the UserDetailService.
 * Spring provides a default version, but for this project we create our own.
 */
@Service
class MyUserDetailsService(
    private val personRepository: PersonRepository
) : UserDetailsService {

    //TODO: have a close look at the implementation of PersonDetails
    override fun loadUserByUsername(username: String): UserDetails {
        val person = personRepository.loadUserByUsername(username)
        if(person.isEmpty) throw UsernameNotFoundException("Could not find person with username: $username")

        return PersonDetails(person.get())
    }
}