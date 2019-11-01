package com.chekh.network.math

import com.chekh.network.math.matrix.Matrix
import kotlin.math.exp
import kotlin.math.pow

object DerivativeFunctions {

    @JvmStatic
    fun derivativeBias(error: Double) = error

    @JvmStatic
    fun derivativeWeight(error: Double, q: Matrix, distances: Matrix): Double =
        exp(-0.5 * u(q, distances)) * error

    @JvmStatic
    fun derivativeCenter(
        centerIndex: Int,
        error: Double,
        weight: Double,
        q: Matrix,
        distances: Matrix
    ): Double {
        var sum = 0.0
        for (index in 0 until q.columnSize) {
            sum += q[centerIndex, index] * z(index, q, distances)
        }
        return -exp(-0.5 * u(q, distances)) * weight * error * sum
    }

    @JvmStatic
    fun derivativeQ(
        rowIndex: Int,
        columnIndex: Int,
        error: Double,
        weight: Double,
        q: Matrix,
        distances: Matrix
    ): Double = -exp(-0.5 * u(q, distances)) * weight * error * distances[rowIndex, 0] * z(columnIndex, q, distances)

    @JvmStatic
    private fun u(q: Matrix, distances: Matrix): Double {
        var sum = 0.0
        for (index in 0 until q.rowSize) {
            sum += z(index, q, distances).pow(2)
        }
        return sum
    }

    @JvmStatic
    private fun z(index: Int, q: Matrix, distances: Matrix): Double {
        var sum = 0.0
        distances.toList(columnIndex = 0).forEachIndexed { distanceIndex, distance ->
            sum += distance * q[distanceIndex, index]
        }
        return sum
    }
}