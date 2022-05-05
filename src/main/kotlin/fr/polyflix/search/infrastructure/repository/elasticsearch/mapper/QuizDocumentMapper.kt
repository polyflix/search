package fr.polyflix.search.infrastructure.repository.elasticsearch.mapper

import fr.polyflix.search.domain.entity.Quiz
import fr.polyflix.search.infrastructure.repository.elasticsearch.document.QuizDocument
import org.springframework.stereotype.Component

@Component
class QuizDocumentMapper: DocumentMapper<Quiz, QuizDocument> {
    override fun toDocument(quiz: Quiz): QuizDocument {
        return QuizDocument(quiz.id, quiz.name)
    }
}