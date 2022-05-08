package fr.polyflix.search.domain.repository

import fr.polyflix.search.domain.entity.Searchable
import fr.polyflix.search.domain.entity.Video
import java.util.UUID

interface ResourceRepository<T: Searchable> {
    /**
     * Create one resource
     */
    fun createOne(video: T)

    /**
     * Delete one resource
     */
    fun deleteOne(id: UUID)
}

typealias VideoRepository = ResourceRepository<Video>