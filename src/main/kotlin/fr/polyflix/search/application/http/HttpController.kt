package fr.polyflix.search.application.http

import fr.polyflix.search.application.dto.SearchResponse
import fr.polyflix.search.domain.entity.Searchable
import fr.polyflix.search.domain.service.SearchService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HttpController(private val searchService: SearchService) {
    @GetMapping("/search", params = ["page", "size", "q"])
    fun index(
        @RequestParam("page") page: Int,
        @RequestParam("size") size: Int,
        @RequestParam("q") query: String
    ): ResponseEntity<SearchResponse<Page<Searchable>>> {
        val searchResult = searchService.search(query, PageRequest.of(page - 1, size))
        return ResponseEntity.ok(SearchResponse(searchResult))
    }
}