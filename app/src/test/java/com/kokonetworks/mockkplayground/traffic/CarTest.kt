package com.kokonetworks.mockkplayground.traffic

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.slot
import org.junit.Assert
import org.junit.Test

/**
 * Created by rowlandoti on 2020-05-22 Last modified 2020-05-22
 */
class CarTest {

    var car1 = Car()

    @Test
    fun calibrateDistance() {
        // given
        val expectedDistance = 20
        mockkObject(SpeedUtils)
        every { SpeedUtils.calibrate(any(), any()) } returns expectedDistance

        val direction = Direction.NORTH()
        // when
        val distance = car1.drive(direction)

        // then
        Assert.assertEquals(expectedDistance, distance /2)
    }

    @Test
    fun calibrateDistance_slot_captured() {
        // given
        val expectedDistance = 20
        mockkObject(SpeedUtils)
        val slot = slot<Int>()
        every { SpeedUtils.calibrate(capture(slot), any()) } returns expectedDistance

        val direction1 = Direction.NORTH()
        // when
        car1.drive(direction1)
        //then
        Assert.assertEquals(direction1.cost, slot.captured)


        val direction2 = Direction.WEST()
        // when
        car1.drive(direction2)
        //then
        Assert.assertEquals(direction2.cost*1.5, slot.captured)
    }

    @Test
    fun calibrateDistance_slot_captured_with_list() {
        // given
        val expectedDistance = 20
        mockkObject(SpeedUtils)
        val list = mutableListOf<Int>()
        every { SpeedUtils.calibrate(capture(list), any()) } returns expectedDistance

        // when
        val direction1 = Direction.NORTH()
        car1.drive(direction1)

        // and when
        val direction2 = Direction.WEST()
        car1.drive(direction2)

        //then
        Assert.assertEquals(direction1.cost, list[0])
        // and then
        Assert.assertEquals(direction2.cost*1.5, list[1])
    }
}