package polar.bear.dashboard.person.signup.reply

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReplyModel
import polar.bear.dashboard.person.signup.dto.SignupResponseDto

class JsonSignupReply(
    valid: Boolean,
    error: JsonError?,
    model: SignupResponseDto?
) : JsonReplyModel<SignupResponseDto>(valid = valid, error = error, model = model)