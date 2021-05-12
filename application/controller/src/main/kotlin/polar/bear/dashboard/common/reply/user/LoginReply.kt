package polar.bear.dashboard.common.reply.user

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReplyModel
import polar.bear.dashboard.person.signIn.dto.SignInResponseDto

class LoginReply(
    valid: Boolean,
    error: JsonError?,
    model: SignInResponseDto
): JsonReplyModel<SignInResponseDto>(valid, error, model)