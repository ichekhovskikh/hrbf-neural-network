package com.chekh.network.math

import com.chekh.network.math.matrix.Matrix
import kotlin.math.exp

object Functions {

    @JvmStatic
    internal fun gaussian(q: Matrix, radius: Matrix): Double =
        exp(-((q * radius).trans() * (q * radius)).single())
}