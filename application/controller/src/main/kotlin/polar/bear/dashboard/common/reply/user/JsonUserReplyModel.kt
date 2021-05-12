package polar.bear.dashboard.common.reply.user

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReplyModel
import polar.bear.dashboard.person.dto.UserProfileDto

class JsonUserReplyModel(
    valid: Boolean,
    error: JsonError?,
    model: UserProfileDto
): JsonReplyModel<UserProfileDto>(valid, error, model)