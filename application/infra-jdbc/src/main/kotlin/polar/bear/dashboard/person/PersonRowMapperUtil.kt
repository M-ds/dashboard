package polar.bear.dashboard.person

import org.springframework.jdbc.core.RowMapper
import polar.bear.dashboard.person.auth.domain.PersonDetail
import polar.bear.dashboard.person.profile.domain.PersonProfile
import polar.bear.dashboard.person.auth.domain.Role
import java.sql.ResultSet

class PersonRowMapperUtil {

    companion object {
        fun personDetailsMapper(): RowMapper<PersonDetail> {
            val finalPerson = PersonDetail()
            val currentUserRoles: MutableList<Role> = mutableListOf()

            return RowMapper<PersonDetail> { rs: ResultSet, _: Int ->
                do {
                    val personUsername = rs.getString("username")
                    val personPassword = rs.getString("password")
                    val personActive = rs.getBoolean("active")
                    if (finalPerson.username.isBlank()) {
                        finalPerson.username = personUsername
                        finalPerson.password = personPassword
                        finalPerson.isActive = personActive
                    }
                    currentUserRoles.add(Role.valueOf(rs.getString("name")))

                } while (rs.next())

                finalPerson.roles = currentUserRoles
                finalPerson

            }
        }

        fun userProfileRowMapper(): RowMapper<PersonProfile> {
            return RowMapper<PersonProfile> { rs: ResultSet, _: Int ->
                PersonProfile(
                    userName = rs.getString("username"),
                    password = rs.getString("password"),
                    email = rs.getString("email")
                )
            }
        }
    }
}