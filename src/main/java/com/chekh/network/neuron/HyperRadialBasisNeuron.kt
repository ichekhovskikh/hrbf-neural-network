package com.chekh.network.neuron

import com.chekh.network.math.Functions.gaussian
import com.chekh.network.math.matrix.Matrix
import com.chekh.network.math.matrix.Matrix.Companion.toMatrix
import java.util.concurrent.ThreadLocalRandom

class HyperRadialBasisNeuron(var inputSize: Int) : Neuron {
    private val q = Matrix(inputSize, inputSize)
    private val centers = MutableList(inputSize) { 0.0 }
    private var weight: Double = ThreadLocalRandom.current().nextDouble()

    init {
        initDiagonalMatrix()
        initCenters()
    }

    private fun initDiagonalMatrix() {
        for (index in 0 until inputSize) {
            q[index, index] = 1.0
        }
    }

    private fun initCenters() {
        val min = -1.0
        val max = 1.0
        val random = ThreadLocalRandom.current()
        for (index in centers.indices) {
            centers[index] = min + random.nextDouble() * (max - min)
        }
    }

    override fun calculate(inputs: List<Double>): Double {
        require(inputs.size == inputSize)
        val radius = inputs.mapIndexed { index, input -> input - centers[index] }.toMatrix()
        return weight * gaussian(q, radius)
    }
}