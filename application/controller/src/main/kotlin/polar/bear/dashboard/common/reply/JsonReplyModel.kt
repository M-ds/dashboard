package polar.bear.dashboard.common.reply

/**
 * This is the base class for all DTO objects.
 * Within this object we can put error messages and so on which should be displayed on screen.
 * @param valid: If the reply contains errors
 * @param error: If reply contains error, specified here otherwise null
 * @param model: DTO representation of the reply.
 */
open class JsonReplyModel<T>(
        private val valid: Boolean,
        private val error: JsonError?,
        private val model: T
)

/**
 * This is the base class for all DTO objects which hold an array of DTO objects
 * @param valid: If the reply contains errors
 * @param error: If reply contains error, specified here otherwise null
 * @param models: DTO representation of the reply.
 */
open class JsonReplyModels<T>(
        private val valid: Boolean,
        private val error: JsonError?,
        private val models: List<T>
)

