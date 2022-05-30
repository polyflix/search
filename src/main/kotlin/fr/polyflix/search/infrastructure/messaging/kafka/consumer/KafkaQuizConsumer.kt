package fr.polyflix.search.infrastructure.messaging.kafka.consumer
import fr.polyflix.search.domain.repository.QuizRepository
import fr.polyflix.search.infrastructure.messaging.kafka.event.QuizEvent
import fr.polyflix.search.infrastructure.messaging.kafka.event.Trigger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class KafkaQuizConsumer(private val quizRepository: QuizRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${spring.kafka.consumer.quiz.topic}"], containerFactory = "kafkaQuizContainerFactory")
    fun onMessage(event: QuizEvent, ack: Acknowledgment) {
        logger.info("Received new event: $event")

        when (event.trigger) {
            Trigger.CREATE, Trigger.UPDATE -> quizRepository.createOne(event.payload)
            Trigger.DELETE -> quizRepository.deleteOne(event.payload.id!!)
        }
        ack.acknowledge()
    }
}