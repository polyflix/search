package fr.polyflix.search.application.http

import fr.polyflix.search.domain.entity.Quiz
import fr.polyflix.search.domain.repository.QuizRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HttpController(private val repo: QuizRepository) {

    @GetMapping("/search")
    fun index() {
        return repo.createOne(Quiz(UUID.randomUUID(), "thomas"))
    }
}