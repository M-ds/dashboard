package polar.bear.dashboard.common.reply.user

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReply
import polar.bear.dashboard.user.dto.UserProfileDto

class JsonUserReply(
    valid: Boolean,
    error: JsonError?,
    model: UserProfileDto
): JsonReply<UserProfileDto>(valid, error, model)