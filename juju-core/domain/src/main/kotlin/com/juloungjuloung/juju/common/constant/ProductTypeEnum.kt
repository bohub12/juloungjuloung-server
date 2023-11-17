package com.juloungjuloung.juju.common.constant

enum class ProductTypeEnum(val desc: String) {
    RING("반지"),
    NECKLACE("목걸이"),
    EARRING("귀걸이"),
    BRACELET("팔찌")
}

const val TYPE_RING = "RING"
const val TYPE_NECKLACE = "NECKLACE"
const val TYPE_EARRING = "EARRING"
const val TYPE_BRACELET = "BRACELET"

// BASE MODEL (타입 구분 없이 조회)
const val TYPE_BASE = "BASE"