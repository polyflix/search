package fr.polyflix.search.infrastructure.messaging.kafka.event

import fr.polyflix.search.domain.entity.Video

class VideoEvent(
    trigger: Trigger,
    val payload: Video
) : ResourceEvent(trigger) {
    override fun toString(): String {
        return "VideoEvent { trigger = $trigger, payload = $payload }"
    }
}

