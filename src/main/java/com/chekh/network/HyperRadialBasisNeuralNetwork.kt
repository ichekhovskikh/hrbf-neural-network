package com.chekh.network

import com.chekh.network.dataset.Dataset
import com.chekh.network.layer.HyperRadialBasisLayer

class HyperRadialBasisNeuralNetwork(override var inputSize: Int, var neuronSize: Int) : NeuralNetwork {

    override val outputSize = OUTPUT_SIZE
    private val radialBasisLayer = HyperRadialBasisLayer(inputSize, neuronSize)
    private var hasInitialized = false

    override fun retrain(dataset: Dataset, epoch: Int, learningRate: Double) {
        init(dataset)
        train(dataset, epoch, learningRate)
    }

    override fun train(dataset: Dataset, epoch: Int, learningRate: Double) {
        initializeIfNeed(dataset)
        for (index in 0 until epoch) {
            // TODO train
        }
    }

    override fun test(dataset: Dataset, accuracyDelta: Double): Float {
        var errors = 0
        dataset.rows.forEach { row ->
            val output = calculate(row.inputs)
            if (output !in (row.output - accuracyDelta)..(row.output + accuracyDelta)) {
                errors++
            }
        }
        return 1f - errors.toFloat() / dataset.rows.size
    }

    override fun calculate(inputs: List<Double>): Double {
        require(inputs.size == inputSize)
        return radialBasisLayer.calculate(inputs)
    }

    private fun initializeIfNeed(dataset: Dataset) {
        if (!hasInitialized) {
            init(dataset)
        }
    }

    private fun init(dataset: Dataset) {
        radialBasisLayer.init(dataset)
        hasInitialized = true
    }

    companion object {
        private const val OUTPUT_SIZE = 1
    }
}