package fr.polyflix.search.infrastructure.repository.elasticsearch.document

import fr.polyflix.search.domain.entity.Video
import fr.polyflix.search.infrastructure.repository.elasticsearch.mapper.DocumentMapper
import fr.polyflix.search.domain.mapper.DomainMapper
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = Indices.VIDEO)
data class VideoDocument (
    @Id val slug: String?,
    @Field(type=FieldType.Text) val title: String?,
    @Field(type=FieldType.Text) val description: String?,
    @Field(type=FieldType.Text) val thumbnail: String?
): DomainMapper<Video> {
    /**
     * Map the VideoDocument to the Video domain entity
     */
    override fun toDomain(): Video {
        return Video(slug, title, description, thumbnail)
    }

    companion object: DocumentMapper<Video, VideoDocument> {
        /**
         * Create a VideoDocument from a Video domain entity
         */
        override fun fromDomain(domain: Video): VideoDocument {
            return VideoDocument(domain.id, domain.title, domain.description, domain.thumbnail)
        }
    }
}