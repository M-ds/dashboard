package polar.bear.dashboard.common.reply

/**
 * This is the base class for all DTO objects.
 * Within this object we can put error messages and so on which should be displayed on screen.
 */
open class JsonReply<T>(
    val valid: Boolean,
    val error: JsonError?,
    val model: T
)

