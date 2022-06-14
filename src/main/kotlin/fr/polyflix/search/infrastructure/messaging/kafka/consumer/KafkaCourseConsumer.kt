package fr.polyflix.search.infrastructure.messaging.kafka.consumer

import fr.polyflix.search.domain.repository.CourseRepository
import fr.polyflix.search.infrastructure.messaging.kafka.event.CourseEvent
import fr.polyflix.search.infrastructure.messaging.kafka.event.Trigger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class KafkaCourseConsumer(private val courseRepository: CourseRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(
        topics = ["\${spring.kafka.consumer.course.topic}"],
        containerFactory = "kafkaCourseContainerFactory"
    )
    fun onMessage(event: CourseEvent, ack: Acknowledgment) {
        logger.info("Received new event: $event")

        // Ignore draft elements and non public elements
        if (event.payload.draft == true || event.payload.visibility != "public") {
            logger.info("The course ${event.payload.id} isn't public or it is in draft state. Ignoring it.")
            courseRepository.deleteOne(event.payload.id!!)
            return ack.acknowledge()
        }

        when (event.trigger) {
            Trigger.CREATE, Trigger.UPDATE -> courseRepository.createOne(event.payload)
            Trigger.DELETE -> courseRepository.deleteOne(event.payload.id!!)
        }
        ack.acknowledge()
    }
}