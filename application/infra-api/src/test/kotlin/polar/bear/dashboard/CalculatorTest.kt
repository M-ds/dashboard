package polar.bear.dashboard

import org.junit.jupiter.api.Test

internal class CalculatorTest {

    @Test
    fun add() {
        val result = Calculator().add(10, 10)
        assert(result == 20)
    }

    @Test
    fun subtract() {
        val result = Calculator().subtract(10, 5)
        assert(result == 5)
    }

    @Test
    fun `subtract first check`() {
        val result = Calculator().subtract(0, 5)
        assert(result == 0)
    }

    @Test
    fun `Multiply correctly`() {
        val result = Calculator().multiply(2, 10)
        assert(result == 20)
    }

    @Test
    fun divide() {
        val result = Calculator().divide(10, 2)
        assert(result == 5)
    }
}