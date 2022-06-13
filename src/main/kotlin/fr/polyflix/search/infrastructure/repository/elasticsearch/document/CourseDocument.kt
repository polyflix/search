package fr.polyflix.search.infrastructure.repository.elasticsearch.document

import fr.polyflix.search.domain.entity.Course
import fr.polyflix.search.infrastructure.repository.elasticsearch.mapper.DocumentMapper
import fr.polyflix.search.domain.mapper.DomainMapper
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = Indices.COURSE)
data class CourseDocument (
    @Id val id: String?,
    @Field(type=FieldType.Text) val name: String?,
    @Field(type=FieldType.Text) val slug: String?,
    @Field(type=FieldType.Text) val shortDescription: String?,
): DomainMapper<Course> {
    /**
     * Map the CourseDocument to the Course domain entity
     */
    override fun toDomain(): Course {
        return Course(id, name, slug, shortDescription)
    }

    companion object: DocumentMapper<Course, CourseDocument> {
        /**
         * Create a QuizDocument from a Quiz domain entity
         */
        override fun fromDomain(domain: Course): CourseDocument {
            return CourseDocument(domain.id, domain.name, domain.slug, domain.shortDescription)
        }
    }
}