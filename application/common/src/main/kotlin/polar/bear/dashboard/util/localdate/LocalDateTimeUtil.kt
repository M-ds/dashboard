package polar.bear.dashboard.util.localdate

import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class LocalDateTimeUtil {
    companion object {
        fun createExpirationDate(minutes: Long) = LocalDateTime.now().plus(Duration.of(minutes, ChronoUnit.MINUTES))
    }
}