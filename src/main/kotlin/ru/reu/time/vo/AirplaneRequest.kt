package ru.reu.time.vo

import java.util.*

data class AirplaneRequest(
    var flightId: UUID? = null,
    var type: TypeAirplaneRequest? = null,
    var hasVips: Boolean = false,
    var hasBaggage: Boolean = false,
    var planeId: UUID? = null,
    var gateNum: Int? = null,
    var refuelNeeded: Boolean? = null
)

enum class TypeAirplaneRequest {
    LANDING, TAKEOFF
}
