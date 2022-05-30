package fr.polyflix.search.infrastructure.messaging.kafka.configuration.consumer

data class Consumer(
    val autoOffsetReset: String,
    val keyDeserializer: String,
    val valueDeserializer: String,
    val video: ConsumerProps,
    val quiz: ConsumerProps,
)
