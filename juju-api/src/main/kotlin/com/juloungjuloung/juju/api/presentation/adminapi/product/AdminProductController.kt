package com.juloungjuloung.juju.api.presentation.adminapi.product

import com.juloungjuloung.juju.api.application.adminapi.product.AdminProductApplicationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/v1/products")
class AdminProductController(
    val adminProductApplicationService: AdminProductApplicationService
) {

    @GetMapping
    fun readProducts() {
    }
}