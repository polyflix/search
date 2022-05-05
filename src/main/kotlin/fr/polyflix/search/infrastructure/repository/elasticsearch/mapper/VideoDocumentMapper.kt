package fr.polyflix.search.infrastructure.repository.elasticsearch.mapper

import fr.polyflix.search.domain.entity.Video
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.VideoDocument
import org.springframework.stereotype.Component

@Component
class VideoDocumentMapper: DocumentMapper<Video, VideoDocument> {
    override fun toDocument(video: Video): VideoDocument {
        return VideoDocument(video.id, video.title)
    }
}