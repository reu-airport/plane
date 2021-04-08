package ru.reu.time.services

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import ru.reu.time.vo.AirplaneRequest
import ru.reu.time.vo.FlightVO
import ru.reu.time.vo.TypeAirplane
import ru.reu.time.vo.TypeAirplaneRequest

@Service
class AirplaneService(
    private val rabbitTemplate: RabbitTemplate
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val mapper = jacksonObjectMapper()

    @RabbitListener(queues = ["airplaneEvent"])
    fun airplaneEvent(message: String) {
        val receivedMessage = mapper.readValue(message, FlightVO::class.java)
        log.info("Received message: $receivedMessage")
        rabbitTemplate.convertAndSend(
            "airplaneRequest",
            mapper.writeValueAsString(
                AirplaneRequest(
                    if (receivedMessage.direction == TypeAirplane.ARRIVAL) TypeAirplaneRequest.TAKEOFF else TypeAirplaneRequest.LANDING,
                    receivedMessage.hasVips,
                    receivedMessage.hasBaggage,
                    receivedMessage.airplane.id,
                    receivedMessage.gateNum,
                    receivedMessage.airplane.refuelNeeded
                )
            )
        )
        log.info("Successful send to handling service flightId: ${receivedMessage.id} and airplaneId: ${receivedMessage.airplane}")
    }

}
