package com.juloungjuloung.juju.entity.member

import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.Entity

@Entity
class MemberEntity(
    val loginId: String,
    val loginPassword: String,
    val nickname: String,
    val profileUrl: String? = null,
    val roles: List<MemberRole>
) : BaseEntity()