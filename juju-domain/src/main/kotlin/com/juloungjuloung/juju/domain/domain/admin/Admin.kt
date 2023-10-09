package com.juloungjuloung.juju.domain.domain.admin

import com.juloungjuloung.juju.domain.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class Admin(
    val loginId: String,
    val loginPassword: String,
    val nickname: String,
    val profileUrl: String?,

    @Enumerated(EnumType.STRING)
    val role: AdminRole
) : BaseEntity()