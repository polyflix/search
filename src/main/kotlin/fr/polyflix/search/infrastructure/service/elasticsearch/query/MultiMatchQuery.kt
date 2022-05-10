package fr.polyflix.search.infrastructure.service.elasticsearch.query

import fr.polyflix.search.infrastructure.repository.elasticsearch.document.QuizDocument
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.VideoDocument
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import kotlin.reflect.full.memberProperties

class MultiMatchQuery {
    companion object {
        private val resources = listOf(
            VideoDocument,
            QuizDocument
        )

        fun create(query: String, pageable: Pageable, excludedFields: List<String> = listOf()): NativeSearchQuery {
            val query = QueryBuilders.multiMatchQuery(query)

            resources.forEach { resource ->
                resource::class.memberProperties
                    .filter { excludedFields.contains(it.name) }
                    .forEach {
                        query.field(it.name)
                    }
            }

            return NativeSearchQueryBuilder()
                .withQuery(query.fuzziness(Fuzziness.ONE).prefixLength(3))
                .withPageable(pageable)
                .build()
        }
    }
}