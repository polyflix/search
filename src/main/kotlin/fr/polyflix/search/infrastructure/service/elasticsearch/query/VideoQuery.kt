package fr.polyflix.search.infrastructure.service.elasticsearch.query

import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class CustomQueryBuilder {
    companion object {
        fun create(clazz: KClass<*>, query: String, pageable: Pageable): NativeSearchQuery {
            val query = QueryBuilders.multiMatchQuery(query)

            clazz.memberProperties.forEach {
                query.field(it.name)
            }

            return NativeSearchQueryBuilder()
                .withQuery(query)
                .withPageable(pageable)
                .build()
        }
    }
}