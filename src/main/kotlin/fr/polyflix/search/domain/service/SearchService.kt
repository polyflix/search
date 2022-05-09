package fr.polyflix.search.domain.service

import fr.polyflix.search.domain.entity.Searchable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SearchService {
    fun search(searchQuery: String, pageable: Pageable): Page<Searchable>
}