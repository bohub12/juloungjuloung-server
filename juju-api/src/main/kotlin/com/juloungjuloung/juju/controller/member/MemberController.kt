package com.juloungjuloung.juju.controller.member

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController {
    @GetMapping("/api/v1")
    fun test(): String {
        return "aa"
    }
}