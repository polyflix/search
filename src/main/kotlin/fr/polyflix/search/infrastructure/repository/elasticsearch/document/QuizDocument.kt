package fr.polyflix.search.infrastructure.repository.elasticsearch.document

import fr.polyflix.search.domain.entity.Quiz
import fr.polyflix.search.infrastructure.repository.elasticsearch.mapper.DocumentMapper
import fr.polyflix.search.domain.mapper.DomainMapper
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = Indices.QUIZ)
data class QuizDocument (
    @Id val id: String?,
    @Field(type=FieldType.Text) val name: String?,
): DomainMapper<Quiz> {
    /**
     * Map the QuizDocument to the Quiz domain entity
     */
    override fun toDomain(): Quiz {
        return Quiz(id, name)
    }

    companion object: DocumentMapper<Quiz, QuizDocument> {
        /**
         * Create a QuizDocument from a Quiz domain entity
         */
        override fun fromDomain(domain: Quiz): QuizDocument {
            return QuizDocument(domain.id, domain.name)
        }
    }
}