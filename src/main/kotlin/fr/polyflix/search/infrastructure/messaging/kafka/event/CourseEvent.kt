package fr.polyflix.search.infrastructure.messaging.kafka.event

import fr.polyflix.search.domain.entity.Course

class CourseEvent(
    trigger: Trigger,
    val payload: Course
) : ResourceEvent(trigger) {
    override fun toString(): String {
        return "CourseEvent { trigger = $trigger, payload = $payload }"
    }
}

