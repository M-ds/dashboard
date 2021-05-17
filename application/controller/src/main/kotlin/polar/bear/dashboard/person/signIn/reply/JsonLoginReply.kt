package polar.bear.dashboard.person.signIn.reply

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReplyModel
import polar.bear.dashboard.person.signIn.dto.SignInResponseDto

class JsonLoginReply(
    valid: Boolean,
    error: JsonError?,
    model: SignInResponseDto?
) : JsonReplyModel<SignInResponseDto>(valid = valid, error = error, model = model)