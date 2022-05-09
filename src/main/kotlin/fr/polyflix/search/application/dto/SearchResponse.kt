package fr.polyflix.search.application.dto

import fr.polyflix.search.domain.entity.Searchable
import org.springframework.data.domain.Page

data class SearchResponse<Data>(val totalPages: Int, val count: Int, val items: List<Searchable>) where Data: Page<Searchable> {
    constructor(data: Data): this(data.totalPages, data.size, data.content)
}