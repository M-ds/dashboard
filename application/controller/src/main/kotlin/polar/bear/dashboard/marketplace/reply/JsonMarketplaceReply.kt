package polar.bear.dashboard.marketplace.reply

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReplyModels
import polar.bear.dashboard.marketplace.dto.MarketPlaceInteractablesDto

class JsonMarketplaceReply(
        valid: Boolean,
        error: JsonError?,
        models: List<MarketPlaceInteractablesDto>
) : JsonReplyModels<MarketPlaceInteractablesDto>(valid, error, models)