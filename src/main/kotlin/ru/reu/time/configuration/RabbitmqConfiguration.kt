package ru.reu.time.configuration

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqConfiguration {

    @Bean
    fun bindings(): Declarables {
        val queue = Queue("airplaneEvent", false)
        val topicExchange = TopicExchange("")
        return Declarables(
            queue,
            BindingBuilder.bind(queue).to(topicExchange).with("airplaneEvent")
        )
    }

}
