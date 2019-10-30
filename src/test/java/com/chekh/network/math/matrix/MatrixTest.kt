package com.chekh.network.math.matrix

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MatrixTest {
    private lateinit var matrix: Matrix
    private lateinit var otherMatrix: Matrix

    @Before
    fun setUp() {
        matrix = Matrix(
            mutableListOf(
                mutableListOf(33.0, 34.0, 12.0),
                mutableListOf(33.0, 19.0, 10.0),
                mutableListOf(12.0, 14.0, 17.0),
                mutableListOf(84.0, 24.0, 51.0),
                mutableListOf(43.0, 71.0, 21.0)
            )
        )
        otherMatrix = Matrix(
            mutableListOf(
                mutableListOf(10.0, 11.0, 34.0, 55.0),
                mutableListOf(33.0, 45.0, 17.0, 81.0),
                mutableListOf(45.0, 63.0, 12.0, 16.0)
            )
        )
    }

    @Test
    fun matrix_trans_test() {
        val result = Matrix(
            mutableListOf(
                mutableListOf(33.0, 33.0, 12.0, 84.0, 43.0),
                mutableListOf(34.0, 19.0, 14.0, 24.0, 71.0),
                mutableListOf(12.0, 10.0, 17.0, 51.0, 21.0)
            )
        )
        assertEquals(matrix.trans(), result)
    }

    @Test
    fun matrix_mul_test() {
        val result = Matrix(
            mutableListOf(
                mutableListOf(1992.0, 2649.0, 1844.0, 4761.0),
                mutableListOf(1407.0, 1848.0, 1565.0, 3514.0),
                mutableListOf(1347.0, 1833.0, 850.0, 2066.0),
                mutableListOf(3927.0, 5217.0, 3876.0, 7380.0),
                mutableListOf(3718.0, 4991.0, 2921.0, 8452.0)
            )
        )
        assertEquals(matrix * otherMatrix, result)
    }
}