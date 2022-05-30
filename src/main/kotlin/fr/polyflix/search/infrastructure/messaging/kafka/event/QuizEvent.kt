package fr.polyflix.search.infrastructure.messaging.kafka.event

import fr.polyflix.search.domain.entity.Quiz

class QuizEvent(
    trigger: Trigger,
    val payload: Quiz
) : ResourceEvent(trigger) {
    override fun toString(): String {
        return "QuizEvent { trigger = $trigger, payload = $payload }"
    }
}

