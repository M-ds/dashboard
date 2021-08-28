package polar.bear.dashboard.util.text

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import polar.bear.dashboard.util.Marker.GIVEN
import polar.bear.dashboard.util.Marker.THEN
import polar.bear.dashboard.util.Marker.WHEN

internal class TextUtilsTest {

    @Test
    fun `Valid characters in text`() {
        GIVEN
        val text = """
            Hello this is just a text
            With a lot of things to say. 
            
            And bla bla bla
        """.trimIndent()

        WHEN
        val valid = TextUtils.hasText(text)

        THEN
        assertTrue(valid)
    }

    @Test
    fun `invalid text`() {
        GIVEN
        val text = null

        WHEN
        val valid = TextUtils.hasText(text)

        THEN
        assertFalse(valid)
    }

    @Test
    fun `Only numbers in text`() {
        GIVEN
        val text = "32156479 9879 1978961 978613 976"

        WHEN
        val valid = TextUtils.hasText(text)

        THEN
        assertTrue(valid)
    }
}