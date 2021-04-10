package ru.reu.time.configuration

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqConfiguration {

    @Bean
    fun bindings(): Declarables {
        val queue = Queue("airplaneVisualEvent", true)
        val queue2 = Queue("airplaneRequest", true)
        val topicExchange = TopicExchange("")
        return Declarables(
            queue,
            queue2,
            BindingBuilder.bind(queue).to(topicExchange).with("airplaneVisualEvent"),
            BindingBuilder.bind(queue2).to(topicExchange).with("airplaneRequest")
        )
    }

}
