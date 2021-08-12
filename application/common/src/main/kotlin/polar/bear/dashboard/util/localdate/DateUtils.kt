package polar.bear.dashboard.util.localdate

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class DateUtils {
    companion object {
        fun createExpirationDate(minutes: Long): LocalDateTime = LocalDateTime.now().plus(
            Duration.of(
                minutes,
                ChronoUnit.MINUTES
            )
        )
    }
}