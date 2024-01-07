package com.juloungjuloung.juju.enums

enum class ProductTypeEnum(val desc: String) {
    RING("반지"),
    NECKLACE("목걸이"),
    EARRING("귀걸이"),
    BRACELET("팔찌"),

    BASE("상품")
}

const val TYPE_RING = "RING"
const val TYPE_NECKLACE = "NECKLACE"
const val TYPE_EARRING = "EARRING"
const val TYPE_BRACELET = "BRACELET"

// BASE MODEL (타입 구분 필요없을 때 사용)
const val TYPE_BASE = "BASE"