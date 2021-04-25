package polar.bear.dashboard.common.reply.marketplace

import polar.bear.dashboard.common.reply.JsonError
import polar.bear.dashboard.common.reply.JsonReplyModel
import polar.bear.dashboard.marketplace.dto.MarketPlaceInteractablesDto

class JsonMarketplaceReply(
        valid: Boolean,
        error: JsonError?,
        models: List<MarketPlaceInteractablesDto>
) : JsonReplyModel<List<MarketPlaceInteractablesDto>>(valid, error, models)