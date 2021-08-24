package polar.bear.dashboard.util.localdate

import java.time.LocalDateTime
import java.time.Month
import java.time.temporal.ChronoUnit
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import polar.bear.dashboard.util.Marker.GIVEN
import polar.bear.dashboard.util.Marker.THEN
import polar.bear.dashboard.util.Marker.WHEN

internal class DateUtilsTest {

    @Test
    fun `Successfully create new timeUtil`() {
        GIVEN
        val currentTime = LocalDateTime.now()

        WHEN
        val updatedTime = DateUtils.createExpirationDate(10)

        THEN
        assertTrue(currentTime.isBefore(updatedTime))
    }

    @Test
    fun `CurrentTime is not before input time`() {
        GIVEN
        val pastTime = LocalDateTime.of(2020, Month.JULY, 1, 1, 1, 1, 1)

        WHEN
        val result = DateUtils.currentTimeIsBeforeTimeToCheck(pastTime)

        THEN
        assertFalse(result)
    }

    @Test
    fun `CurrentTime is before input time`() {
        GIVEN
        val pastTime = LocalDateTime.now().plus(10, ChronoUnit.MINUTES)

        WHEN
        val result = DateUtils.currentTimeIsBeforeTimeToCheck(pastTime)

        THEN
        assertTrue(result)
    }

    @Test
    fun `Time is expired`() {
        GIVEN
        val time = LocalDateTime.now().minusMinutes(10)

        WHEN
        val result = DateUtils.currentTimeIsAfterTimeToCheck(time)

        THEN
        assertTrue(result)
    }
}