package com.juloungjuloung.juju.domain.member

import com.juloungjuloung.juju.domain.BaseEntity
import jakarta.persistence.Entity

@Entity
class Member(
    val loginId: String,
    val loginPassword: String,
    val nickname: String,
    val profileUrl: String? = null
) : BaseEntity()