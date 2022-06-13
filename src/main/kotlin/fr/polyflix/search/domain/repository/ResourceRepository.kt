package fr.polyflix.search.domain.repository

import fr.polyflix.search.domain.entity.*

interface ResourceRepository<T: Searchable> {
    /**
     * Create one resource
     */
    fun createOne(video: T)

    /**
     * Delete one resource
     */
    fun deleteOne(id: String)
}

typealias VideoRepository = ResourceRepository<Video>
typealias QuizRepository = ResourceRepository<Quiz>
typealias UserRepository = ResourceRepository<User>
typealias ModuleRepository = ResourceRepository<Module>
typealias CourseRepository = ResourceRepository<Course>
