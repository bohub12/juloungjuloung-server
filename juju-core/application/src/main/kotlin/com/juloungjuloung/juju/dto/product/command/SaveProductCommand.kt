package com.juloungjuloung.juju.dto.product.command

data class SaveProductCommand(
    val productType: String,
    val name: String,
    val price: Long,
    val weightByMilliGram: Long,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int?,

    val additionalBraceletCommand: SaveBraceletAdditionalCommand?,
    val additionalNecklaceCommand: SaveNecklaceAdditionalCommand?
)

data class SaveBraceletAdditionalCommand(
    val maximumLength: Int,
    val minimumLength: Int
)

data class SaveNecklaceAdditionalCommand(
    val maximumLength: Int,
    val minimumLength: Int
)