package ru.reu.time.vo

import java.util.*

data class AirplaneVisualEvent(
    var planeId: UUID? = null,
    var type: TypeAirplaneRequest? = null
)
