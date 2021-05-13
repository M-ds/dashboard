package polar.bear.dashboard.util.text

/**
 * This class has some helper functions which can be utilised throughout the application.
 */
class TextUtils {

    companion object {

        fun hasText(string: String?): Boolean {
            if (string.isNullOrBlank()) return false
            return containsText(string)
        }

        private fun containsText(str: String): Boolean {
            val strLen: Int = str.length

            for (i in 0 until strLen) {
                if (!Character.isWhitespace(str[i])) {
                    return true
                }
            }
            return false
        }
    }
}