package com.chekh.network.learning

import com.chekh.network.HyperRadialBasisNeuralNetwork
import com.chekh.network.dataset.Dataset
import com.chekh.network.layer.HyperRadialBasisLayer
import com.chekh.network.log.ChartDrawer
import com.chekh.network.log.Point
import com.chekh.network.math.DerivativeFunctions.derivativeBias
import com.chekh.network.math.DerivativeFunctions.derivativeCenter
import com.chekh.network.math.DerivativeFunctions.derivativeQ
import com.chekh.network.math.DerivativeFunctions.derivativeWeight
import com.chekh.network.math.matrix.Matrix
import com.chekh.network.math.matrix.Matrix.Companion.toMatrix
import com.chekh.network.neuron.HyperRadialBasisNeuron

class BackPropagationLearningStrategy : HyperRadialBasisLearningStrategy {

    override fun train(
        network: HyperRadialBasisNeuralNetwork,
        dataset: Dataset,
        epoch: Int,
        learningRate: Double,
        errorDrawer: ChartDrawer?
    ) {
        var iteration = 0.0
        errorDrawer?.clear()
        for (index in 0 until epoch) {
            dataset.rows.shuffled().forEach { data ->
                val output = network.radialBasisLayer.calculate(data.inputs)
                val error = output - data.output
                correct(network, data.inputs, error, learningRate)
                errorDrawer?.draw(Point(iteration++, error))
            }
        }
    }

    private fun correct(
        network: HyperRadialBasisNeuralNetwork,
        inputs: List<Double>,
        error: Double,
        learningRate: Double
    ) {
        val layer = network.radialBasisLayer
        correctBias(layer, error, learningRate)
        layer.neurons.forEach { neuron ->
            val qCopy = neuron.q.copy()
            val distances = inputs.mapIndexed { index, input -> input - neuron.centers[index] }.toMatrix()
            val weightCopy = neuron.weight
            correctWeight(neuron, qCopy, distances, error, learningRate)
            correctCenters(neuron, qCopy, distances, weightCopy, error, learningRate)
            correctQ(neuron, qCopy, distances, weightCopy, error, learningRate)
        }
    }

    private fun correctBias(layer: HyperRadialBasisLayer, error: Double, learningRate: Double) {
        layer.bias -= learningRate * derivativeBias(error)
    }

    private fun correctWeight(
        neuron: HyperRadialBasisNeuron,
        q: Matrix,
        distances: Matrix,
        error: Double,
        learningRate: Double
    ) {
        neuron.weight -= learningRate * derivativeWeight(error, q, distances)
    }

    private fun correctCenters(
        neuron: HyperRadialBasisNeuron,
        q: Matrix,
        distances: Matrix,
        weight: Double,
        error: Double,
        learningRate: Double
    ) {
        for (centerIndex in 0 until neuron.centers.size) {
            neuron.centers[centerIndex] -= learningRate *
                    derivativeCenter(centerIndex, error, weight, q, distances)
        }
    }

    private fun correctQ(
        neuron: HyperRadialBasisNeuron,
        q: Matrix,
        distances: Matrix,
        weight: Double,
        error: Double,
        learningRate: Double
    ) {
        for (rowIndex in 0 until q.rowSize) {
            for (columnIndex in 0 until q.columnSize) {
                neuron.q[rowIndex, columnIndex] -= learningRate *
                        derivativeQ(rowIndex, columnIndex, error, weight, q, distances)
            }
        }
    }
}