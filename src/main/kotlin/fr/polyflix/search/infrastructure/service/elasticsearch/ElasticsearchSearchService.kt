package fr.polyflix.search.infrastructure.service.elasticsearch

import fr.polyflix.search.domain.entity.Searchable
import fr.polyflix.search.domain.mapper.DomainMapper
import fr.polyflix.search.domain.service.SearchService
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.VideoDocument
import fr.polyflix.search.infrastructure.service.elasticsearch.query.CustomQueryBuilder
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.SearchHitSupport
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.stereotype.Service

@Service
class ElasticsearchSearchService(private val es: ElasticsearchRestTemplate): SearchService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun search(searchQuery: String, pageable: Pageable): Page<Searchable> {
        logger.info("Building query to perform search request with data: { query = $searchQuery }")

        val videoQuery = CustomQueryBuilder.create(VideoDocument::class, searchQuery, pageable)

        val queries = listOf(videoQuery)
        val documents = listOf(VideoDocument::class.java)

        logger.info("Performing search request on Elasticsearch")
        return try {
            // Get all the hits matching the query
            val hits = es.multiSearch(queries, documents)

            // Mapping every document into Domain specific entity
            val data = hits.flatMap { it.searchHits.map { item -> (item.content as DomainMapper<*>).toDomain() } } as List<Searchable>

            PageImpl(data, pageable, data.size.toLong())
        } catch (e: Exception) {
            logger.error("Failed to perform search request on Elasticsearch. Details = ${e.message}")
            Page.empty()
        }
    }
}