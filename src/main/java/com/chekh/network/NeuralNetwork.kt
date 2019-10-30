package com.chekh.network

interface NeuralNetwork {
    val inputSize: Int
    val outputSize: Int
    fun calculate(inputs: List<Double>): Double
}