package org.acme.kogito.model

import java.math.BigDecimal

data class ImportantPrices(val prices: Collection<BigDecimal>) {
    companion object {
        @JvmStatic
        fun fromDouble(vararg doublePrices: Double): ImportantPrices {
            return ImportantPrices(doublePrices.map { BigDecimal(it) })
        }
    }
}

data class Offer(val pricePerWidget: BigDecimal, var priceValidationDecision: PriceValidationDecision)