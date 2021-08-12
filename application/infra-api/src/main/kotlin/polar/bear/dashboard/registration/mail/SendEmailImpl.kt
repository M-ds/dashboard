package polar.bear.dashboard.registration.mail

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import polar.bear.dashboard.person.signup.infra.SendEmail

class SendEmailImpl(
    val mailSender: JavaMailSender,
    val fromEmailAddress: String,
    val companyName: String
) : SendEmail {

    override fun sendEmail(request: SendEmail.Request) {
        val toAddress = request.email
        val subject = "Registration Email"

        val verifyURL = "${request.verifyUrl}/user/auth/_verify?code=${request.token}"

        val content = """
            Dear ${request.username}, <br>
            Please click the link below to verify your email address:
            <br>
            <h3><a href="$verifyURL" target="_self">Verify</a></h3>
            <br>
            See you soon!<br>
            $companyName            
        """.trimIndent()

        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom(fromEmailAddress, companyName)
        helper.setTo(toAddress)
        helper.setSubject(subject)
        helper.setText(content, true)

        mailSender.send(message)
    }
}