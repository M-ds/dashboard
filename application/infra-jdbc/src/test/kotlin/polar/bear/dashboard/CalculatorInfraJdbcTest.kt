package polar.bear.dashboard

import org.junit.jupiter.api.Test

 internal class CalculatorInfraJdbcTest {

    @Test
    fun add() {
        val result = CalculatorInfraJdbc().add(10, 10)
        assert(result == 20)
    }

    @Test
    fun subtract() {
        val result = CalculatorInfraJdbc().subtract(10, 5)
        assert(result == 5)
    }

    @Test
    fun `subtract first check`() {
        val result = CalculatorInfraJdbc().subtract(0, 5)
        assert(result == 0)
    }

    @Test
    fun `Multiply correctly`() {
        val result = CalculatorInfraJdbc().multiply(2, 10)
        assert(result == 20)
    }

    @Test
    fun divide() {
        val result = CalculatorInfraJdbc().divide(10, 2)
        assert(result == 5)
    }
}