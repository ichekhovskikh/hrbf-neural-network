package com.chekh.network

import com.chekh.network.learning.BackPropagationLearningStrategy
import com.chekh.network.learning.HyperRadialBasisLearningStrategy

class HyperRadialBasisNeuralNetworkBuilder {
    private var inputSize = 1
    private var neuronSize = 1
    private var learningStrategy: HyperRadialBasisLearningStrategy = BackPropagationLearningStrategy()

    fun withInputSize(inputSize: Int): HyperRadialBasisNeuralNetworkBuilder = apply { this.inputSize = inputSize }

    fun withNeuronSize(neuronSize: Int): HyperRadialBasisNeuralNetworkBuilder = apply { this.neuronSize = neuronSize }

    fun withLearningStrategy(learningStrategy: HyperRadialBasisLearningStrategy): HyperRadialBasisNeuralNetworkBuilder =
        apply {
            this.learningStrategy = learningStrategy
        }

    fun build(): HyperRadialBasisNeuralNetwork {
        require(inputSize > 0 && neuronSize > 0)
        return HyperRadialBasisNeuralNetwork(inputSize, neuronSize, learningStrategy)
    }
}