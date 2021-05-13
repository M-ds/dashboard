package polar.bear.dashboard.marketplace

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import polar.bear.dashboard.marketplace.reply.JsonMarketplaceReply
import polar.bear.dashboard.marketplace.dto.MarketPlaceInteractablesDto

@RestController
class MarketplaceController {

    @GetMapping("/api/marketplace")
    fun interactablesAvailable(): JsonMarketplaceReply {
        val arrayOfInteractablesForMarketplace = arrayListOf<MarketPlaceInteractablesDto>()
        arrayOfInteractablesForMarketplace.add(MarketPlaceInteractablesDto(1, "test", "Description", "testUrl"))
        arrayOfInteractablesForMarketplace.add(MarketPlaceInteractablesDto(2, "test-2", "Description-2", "testUrl-2"))
        return JsonMarketplaceReply(
                valid = true,
                error = null,
                models = arrayOfInteractablesForMarketplace
        )
    }
}