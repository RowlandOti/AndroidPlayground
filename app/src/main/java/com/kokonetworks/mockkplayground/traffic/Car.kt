package com.kokonetworks.mockkplayground.traffic

/**
 * Created by rowlandoti on 2020-05-22
 * Last modified 2020-05-22
 */
class Car {

    fun drive(direction: Direction): Int {
        when (direction) {
            is Direction.NORTH -> {
                return calibrateDistance(direction.cost)
            }
            is Direction.SOUTH -> {
                return calibrateDistance(direction.cost)
            }
            is Direction.EAST -> {
                return calibrateDistance(direction.cost)
            }
            is Direction.WEST -> {
                return calibrateDistance(direction.cost)
            }
        }
    }

    fun calibrateDistance(cost:Int) :Int {
        return SpeedUtils.calibrate(cost, 2)
    }
}