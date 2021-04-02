package polar.bear.dashboard

class CalculatorCore {
    fun add(number1: Int, number2: Int): Int {
        return number1 + number2
    }

    fun subtract(number1: Int, number2: Int): Int {
        if (number1 == 0 || number2 == 0) {
            return 0
        }
        return number1 - number2
    }

    fun multiply(number1: Int, number2: Int): Int {
        if (number1 == null) {
            return 0
        }
        return number1 * number2
    }

    fun divide(number1: Int, number2: Int): Int {
        return number1 / number2
    }
}