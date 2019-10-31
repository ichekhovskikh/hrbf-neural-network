package com.chekh.network

class HyperRadialBasisNeuralNetworkBuilder {
    private var inputSize = 1
    private var neuronSize = 1

    fun withInputSize(inputSize: Int): HyperRadialBasisNeuralNetworkBuilder = apply { this.inputSize = inputSize }

    fun withNeuronSize(neuronSize: Int): HyperRadialBasisNeuralNetworkBuilder = apply { this.neuronSize = neuronSize }

    fun build(): HyperRadialBasisNeuralNetwork {
        require(inputSize > 0 && neuronSize > 0)
        return HyperRadialBasisNeuralNetwork(inputSize, neuronSize)
    }
}