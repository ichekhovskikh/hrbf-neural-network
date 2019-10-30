package com.chekh.network.layer

import com.chekh.network.neuron.HyperRadialBasisNeuron
import java.util.concurrent.ThreadLocalRandom

class HyperRadialBasisLayer(var inputSize: Int, var neuronSize: Int) : Layer {
    private val neurons = List(neuronSize) { HyperRadialBasisNeuron(inputSize) }
    private var bias = ThreadLocalRandom.current().nextDouble()

    override fun calculate(inputs: List<Double>): Double {
        require(inputs.size == inputSize)
        return bias + neurons.sumByDouble { neuron -> neuron.calculate(inputs) }
    }
}