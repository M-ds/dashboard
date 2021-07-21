package polar.bear.dashboard.config.infra.mail

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import polar.bear.dashboard.person.signup.infra.SendEmail
import polar.bear.dashboard.registration.mail.SendEmailImpl

@Configuration
open class MailConfiguration {

    @Value("\${dashboard.company.email.from}")
    private val fromEmailAddress: String = ""

    @Value("\${dashboard.company.name}")
    private val companyName: String = ""

    @Bean
    open fun sendMailConfiguration(
        mailSender: JavaMailSender
    ): SendEmail{
        return SendEmailImpl(
            mailSender = mailSender,
            fromEmailAddress = fromEmailAddress,
            companyName = companyName
        )
    }
}