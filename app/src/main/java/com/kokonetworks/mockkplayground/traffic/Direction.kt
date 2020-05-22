package com.kokonetworks.mockkplayground.traffic

/**
 * Created by rowlandoti on 2020-05-22
 * Last modified 2020-05-22
 */

sealed class Direction(var cost: Int = -1) {
    class NORTH(cost: Int = 0) : Direction(cost)
    class SOUTH(cost: Int = 1) : Direction(cost)
    class EAST(cost: Int = 2) : Direction(cost)
    class WEST(cost: Int = 3) : Direction(cost)
}
