package fr.polyflix.search.application.dto

import fr.polyflix.search.domain.entity.Searchable
import org.springframework.data.domain.Page

data class SearchResponse<Data>(
    val totalElements: Long,
    val totalPages: Int,
    val currentPage: Int,
    val results: List<Searchable>
) where Data : Page<Searchable> {
    constructor(data: Data) : this(data.totalElements, data.totalPages , data.number + 1, data.content)
}