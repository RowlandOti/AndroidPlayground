package com.kokonetworks.mockkplayground.traffic

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import org.junit.Before
import org.junit.Test


/**
 * Created by rowlandoti on 2020-05-22 Last modified $file.lastModified
 */
class TrafficSystemTest {

    @MockK
    lateinit var car1: Car

    @RelaxedMockK
    lateinit var car2: Car

    @MockK(relaxed = true)
    lateinit var car3: Car

    @MockK(relaxed = true)
    lateinit var carPrivate: Car

    @MockK(relaxUnitFun = true)
    lateinit var car4: Car

    @SpyK
    var car5 = Car()

    // Automatically matches dependencies wby names i.e car1 == trafficSystem.car1 - and so on
    // Matches properties by trying in the following order - name, class then superclass
    //@InjectMockKs(overrideValues = true) // set even already initialized fields
    @InjectMockKs()
    var trafficSystem = TrafficSystem()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun drive_one_car_mockked() {
        val direction = Direction.NORTH()
        //every { car1.drive(any<Direction>()) } returns direction.cost
        trafficSystem.drive(trafficSystem.car1, direction)
        verify { car1.drive(direction) }
    }

    @Test
    fun drive_one_car_relaxed_mockked() {
        val direction = Direction.NORTH()
        trafficSystem.drive(trafficSystem.car2, direction)
        verify { car2.drive(direction) }
    }

    @Test
    fun drive_one_car_relaxed_arg_mockked() {
        val direction = Direction.NORTH()
        trafficSystem.drive(trafficSystem.car3, direction)
        verify { car3.drive(direction) }
    }

    // You won't have to provide answers, for the method under test as it calls the real one
    @Test
    fun drive_one_car_spied() {
        val direction = Direction.NORTH()
        trafficSystem.drive(car5, direction)
        verify { car5.drive(direction) }
    }

    @Test
    fun drive_one_car_confirm_verified() {
        val direction1 = Direction.NORTH()
        trafficSystem.drive(car2, direction1)

        val direction2 = Direction.SOUTH()
        trafficSystem.drive(car2, direction2)

        verify { car2.drive(direction1)
            //car2.drive(direction2)
        }

        // To double check that all method calls on mock were verified by verify
        confirmVerified(car2)
    }

    @Test
    fun drive_one_car_verifyAll() {
        val direction1 = Direction.NORTH()
        trafficSystem.drive(car2, direction1)

        val direction2 = Direction.SOUTH()
        trafficSystem.drive(car2, direction2)

        verifyAll { //car2.drive(direction1)
            car2.drive(direction2)
        }
    }

    @Test
    fun drive_one_car_verifyOrder() {
        val direction1 = Direction.NORTH()
        trafficSystem.drive(car2, direction1)

        val direction2 = Direction.SOUTH()
        trafficSystem.drive(car2, direction2)

        val direction3 = Direction.EAST()
        trafficSystem.drive(car2, direction3)

        verifyOrder { car3.drive(direction2)
            car2.drive(direction3)
        }
    }

    @Test
    fun drive_one_car_verifySequence() {
        val direction1 = Direction.NORTH()
        trafficSystem.drive(car2, direction1)

        val direction2 = Direction.NORTH()
        trafficSystem.drive(car2, direction2)

        verifySequence { car2.drive(direction2)
            car2.drive(direction1)
        }
    }

    @Test
    fun drive_one_car_timeout() {
        val timeout = 3000L
        val direction1 = Direction.NORTH()
        trafficSystem.drive(car2, direction1, timeout)

        verify (timeout = 2500){ car2.drive(direction1) }
    }


    @Test
    fun drive_one_private_car_relaxed_mockked() {
        //trafficSystem.car1 = car1
        //trafficSystem.carPrivate = carPrivate
        val direction1 = Direction.NORTH()
        trafficSystem.drive(trafficSystem.carPrivate, direction1)

        verify { carPrivate.drive(direction1) }
    }


    @Test
    fun drive_many_cars() {

    }
}