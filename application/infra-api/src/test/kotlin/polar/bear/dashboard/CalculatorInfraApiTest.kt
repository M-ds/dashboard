package polar.bear.dashboard

import org.junit.jupiter.api.Test

 internal class CalculatorInfraApiTest {

    @Test
    fun add() {
        val result = CalculatorCommon().add(10, 10)
        assert(result == 20)
    }

    @Test
    fun subtract() {
        val result = CalculatorCommon().subtract(10, 5)
        assert(result == 5)
    }

    @Test
    fun `subtract first check`() {
        val result = CalculatorCommon().subtract(0, 5)
        assert(result == 0)
    }

    @Test
    fun `Multiply correctly`() {
        val result = CalculatorCommon().multiply(2, 10)
        assert(result == 20)
    }

    @Test
    fun divide() {
        val result = CalculatorCommon().divide(10, 2)
        assert(result == 5)
    }
}