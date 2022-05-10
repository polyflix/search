package fr.polyflix.search.infrastructure.service.elasticsearch

import fr.polyflix.search.domain.entity.Searchable
import fr.polyflix.search.domain.mapper.DomainMapper
import fr.polyflix.search.domain.service.SearchService
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.Indices
import fr.polyflix.search.infrastructure.service.elasticsearch.query.MultiMatchQuery
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.SearchHitSupport
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.stereotype.Service

@Service
class ElasticsearchSearchService(private val es: ElasticsearchRestTemplate) : SearchService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun search(searchQuery: String, pageable: Pageable): Page<Searchable> {
        logger.info("Building query to perform search request with data: { query = $searchQuery }")

        val query = MultiMatchQuery.create(searchQuery, pageable)

        return try {
            // Get all the hits matching the query
            val hits = es.search(query, Any::class.java, IndexCoordinates.of(Indices.coordinates()))
            val paged = SearchHitSupport.searchPageFor(hits, pageable)
            // Mapping every document into Domain specific entity
            val data = hits.searchHits.map { (it.content as DomainMapper<*>).toDomain() } as List<Searchable>

            logger.info(paged.toString())

            PageImpl(data, paged.pageable, paged.totalElements)
        } catch (e: Exception) {
            logger.error("Failed to perform search request on Elasticsearch. Details = ${e.message}")
            Page.empty()
        }
    }
}