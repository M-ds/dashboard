package polar.bear.dashboard

import org.junit.jupiter.api.Test

internal class CalculatorCoreTest {

    @Test
    fun add() {
        val result = CalculatorCore().add(10, 10)
        assert(result == 20)
    }

    @Test
    fun subtract() {
        val result = CalculatorCore().subtract(10, 5)
        assert(result == 5)
    }

    @Test
    fun `subtract first check`() {
        val result = CalculatorCore().subtract(0, 5)
        assert(result == 0)
    }

    @Test
    fun `Multiply correctly`() {
        val result = CalculatorCore().multiply(2, 10)
        assert(result == 20)
    }

    @Test
    fun divide() {
        val result = CalculatorCore().divide(10, 2)
        assert(result == 5)
    }
}