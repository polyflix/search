package fr.polyflix.search.infrastructure.repository.elasticsearch.document

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.*

@Document(indexName = "quizzes")
data class QuizDocument(
    @Id val id: UUID,
    @Field(type= FieldType.Text) val name: String
)