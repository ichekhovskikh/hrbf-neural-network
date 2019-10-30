package com.chekh.network

import com.chekh.network.layer.HyperRadialBasisLayer

class HyperRadialBasisNeuralNetwork(override var inputSize: Int, var neuronSize: Int) : NeuralNetwork {

    override val outputSize = OUTPUT_SIZE

    private val radialBasisLayer = HyperRadialBasisLayer(inputSize, neuronSize)

    override fun calculate(inputs: List<Double>): Double {
        require(inputs.size == inputSize)
        return radialBasisLayer.calculate(inputs)
    }

    companion object {
        private const val OUTPUT_SIZE = 1
    }
}