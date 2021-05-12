package polar.bear.dashboard.person.profile.reply

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReplyModel
import polar.bear.dashboard.person.profile.dto.UserProfileDto

class JsonUserProfileReplyModel(
    valid: Boolean,
    error: JsonError?,
    model: UserProfileDto
): JsonReplyModel<UserProfileDto>(valid, error, model)