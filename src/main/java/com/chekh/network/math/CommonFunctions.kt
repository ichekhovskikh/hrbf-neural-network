package com.chekh.network.math

import com.chekh.network.math.matrix.Matrix
import kotlin.math.exp

object CommonFunctions {

    @JvmStatic
    internal fun gaussian(q: Matrix, distances: Matrix): Double =
        exp(-0.5 * ((q * distances).trans() * (q * distances)).single())
}