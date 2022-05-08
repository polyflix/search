package fr.polyflix.search.application.http

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HttpController {

    @GetMapping("/search")
    fun index(): String {
        return "TODO: Search for items"
    }
}