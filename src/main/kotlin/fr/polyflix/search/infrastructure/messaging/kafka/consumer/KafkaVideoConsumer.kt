package fr.polyflix.search.infrastructure.messaging.kafka.consumer

import fr.polyflix.search.domain.repository.VideoRepository
import fr.polyflix.search.infrastructure.messaging.kafka.event.Trigger
import fr.polyflix.search.infrastructure.messaging.kafka.event.VideoEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class KafkaVideoConsumer(private val videoRepository: VideoRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${spring.kafka.consumer.video.topic}"], containerFactory = "kafkaVideoContainerFactory")
    fun onMessage(event: VideoEvent, ack: Acknowledgment) {
        logger.info("Received new event: $event")

        // Ignore draft elements and non public elements
        if (event.payload.draft == true || event.payload.visibility != "public") {
            logger.info("The video ${event.payload.id} isn't public or it is in draft state. Ignoring it.")
            videoRepository.deleteOne(event.payload.id!!)
            return ack.acknowledge()
        }

        when (event.trigger) {
            Trigger.CREATE, Trigger.UPDATE -> videoRepository.createOne(event.payload)
            Trigger.DELETE -> videoRepository.deleteOne(event.payload.id!!)
        }

        ack.acknowledge()
    }
}