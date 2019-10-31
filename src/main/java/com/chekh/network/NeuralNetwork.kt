package com.chekh.network

import com.chekh.network.dataset.Dataset

interface NeuralNetwork {
    val inputSize: Int
    val outputSize: Int
    fun retrain(dataset: Dataset, epoch: Int, learningRate: Double)
    fun train(dataset: Dataset, epoch: Int, learningRate: Double)
    fun test(dataset: Dataset, accuracyDelta: Double): Float
    fun calculate(inputs: List<Double>): Double
}