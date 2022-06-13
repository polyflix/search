package fr.polyflix.search.infrastructure.repository.elasticsearch.document

import fr.polyflix.search.domain.entity.Module
import fr.polyflix.search.infrastructure.repository.elasticsearch.mapper.DocumentMapper
import fr.polyflix.search.domain.mapper.DomainMapper
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = Indices.MODULE)
data class ModuleDocument (
    @Id val id: String?,
    @Field(type=FieldType.Text) val name: String?,
    @Field(type=FieldType.Text) val slug: String?,
    @Field(type=FieldType.Text) val description: String?,
): DomainMapper<Module> {
    /**
     * Map the CourseDocument to the Course domain entity
     */
    override fun toDomain(): Module {
        return Module(id, name, slug, description)
    }

    companion object: DocumentMapper<Module, ModuleDocument> {
        /**
         * Create a QuizDocument from a Quiz domain entity
         */
        override fun fromDomain(domain: Module): ModuleDocument {
            return ModuleDocument(domain.id, domain.name, domain.slug, domain.description)
        }
    }
}