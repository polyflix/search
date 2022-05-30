package fr.polyflix.search.infrastructure.messaging.kafka.event

import fr.polyflix.search.domain.entity.User

class UserEvent(
    trigger: Trigger,
    val payload: User
) : ResourceEvent(trigger) {
    override fun toString(): String {
        return "UserEvent { trigger = $trigger, payload = $payload }"
    }
}