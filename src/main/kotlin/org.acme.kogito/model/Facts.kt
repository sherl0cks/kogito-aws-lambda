package org.acme.kogito.model

import java.math.BigDecimal

data class ImportantPrices(val priceType: String, val prices: Collection<BigDecimal>) {
    companion object {
        @JvmStatic
        fun fromDouble(priceType: String, vararg doublePrices: Double): ImportantPrices {
            return ImportantPrices(priceType, doublePrices.map { BigDecimal(it) })
        }
    }
}

data class Offer(val pricePerWidget: BigDecimal, var priceValidationDecision: PriceValidationDecision)