package polar.bear.dashboard.person.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import polar.bear.dashboard.person.auth.domain.PersonDetail

class PersonDetails(
    private val personDetail: PersonDetail
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return personDetail.roles.map { role ->
            SimpleGrantedAuthority(role.name)
        }.toMutableList()
    }

    override fun getPassword(): String {
        return personDetail.password
    }

    override fun getUsername(): String {
        return personDetail.username
    }

    override fun isAccountNonExpired(): Boolean {
        return personDetail.isActive
    }

    override fun isAccountNonLocked(): Boolean {
        return personDetail.isActive
    }

    override fun isCredentialsNonExpired(): Boolean {
        return personDetail.isActive
    }

    override fun isEnabled(): Boolean {
        return personDetail.isActive
    }
}