package com.example.currentlocation.model

data class Route(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<RouteX>,
    val status: String
)