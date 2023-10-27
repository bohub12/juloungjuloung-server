package com.juloungjuloung.juju.domain.member

import com.juloungjuloung.juju.member.MemberRole

data class Member(
    val loginId: String,
    val loginPassword: String,
    val nickname: String,
    val profileUrl: String? = null,
    val roles: List<MemberRole>
)