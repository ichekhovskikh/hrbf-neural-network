package com.chekh.network.neuron

import com.chekh.network.dataset.Dataset
import com.chekh.network.math.CommonFunctions.gaussian
import com.chekh.network.math.matrix.Matrix
import com.chekh.network.math.matrix.Matrix.Companion.toMatrix
import java.util.concurrent.ThreadLocalRandom

class HyperRadialBasisNeuron(var inputSize: Int) : Neuron {
    val q = Matrix(inputSize, inputSize)
    val centers = MutableList(inputSize) { 0.0 }
    var weight: Double = 0.0

    fun init(dataset: Dataset, isPositiveRandom: Boolean = true) {
        initWeight()
        initDiagonalMatrix()
        initCenters(dataset, isPositiveRandom)
    }

    private fun initWeight() {
        weight = ThreadLocalRandom.current().nextDouble()
    }

    private fun initDiagonalMatrix() {
        for (rowIndex in 0 until q.rowSize) {
            for (columnIndex in 0 until q.columnSize) {
                q[rowIndex, columnIndex] = if (rowIndex == columnIndex) 1.0 else 0.0
            }
        }
    }

    private fun initCenters(dataset: Dataset, isPositiveRandom: Boolean) {
        val random = ThreadLocalRandom.current()
        for (index in centers.indices) {
            val randomValue = if (isPositiveRandom) random.nextDouble() else -random.nextDouble()
            centers[index] = dataset.getInputParams(index).average() + randomValue
        }
    }

    override fun calculate(inputs: List<Double>): Double {
        require(inputs.size == inputSize)
        val distances = inputs.mapIndexed { index, input -> input - centers[index] }.toMatrix()
        return weight * gaussian(q, distances)
    }
}