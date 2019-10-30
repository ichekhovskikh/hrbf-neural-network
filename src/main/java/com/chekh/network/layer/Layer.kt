package com.chekh.network.layer

interface Layer {
    fun calculate(inputs: List<Double>): Double
}