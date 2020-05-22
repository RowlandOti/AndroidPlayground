package com.kokonetworks.mockkplayground.traffic

/**
 * Created by rowlandoti on 2020-05-22
 * Last modified 2020-05-22
 */
class TrafficSystem {

    lateinit var car1: Car

    lateinit var car2: Car

    lateinit var car3: Car

    lateinit var carPrivate: Car
        private set

    fun drive(car: Car, direction: Direction) {
        car.drive(direction)
    }

    fun drive(cars: List<Car>, direction: Direction) {
        cars.forEach {
            it.drive(direction)
        }
    }

    fun drive(car: Car, direction: Direction,
              timeOutMs: Long) {

        Thread{
            Thread.sleep(timeOutMs)
            car.drive(direction)
        }.start()
    }
}