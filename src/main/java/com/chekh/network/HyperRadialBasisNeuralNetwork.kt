package com.chekh.network

import com.chekh.network.dataset.Dataset
import com.chekh.network.dataset.DatasetNormalizer
import com.chekh.network.layer.HyperRadialBasisLayer
import com.chekh.network.learning.HyperRadialBasisLearningStrategy
import com.chekh.network.log.ChartDrawer
import com.chekh.network.log.Logger

class HyperRadialBasisNeuralNetwork(
    override var inputSize: Int,
    var neuronSize: Int,
    var learningStrategy: HyperRadialBasisLearningStrategy,
    var normalizer: DatasetNormalizer,
    var logger: Logger? = null,
    var errorDrawer: ChartDrawer? = null
    ) : NeuralNetwork {

    override val outputSize = OUTPUT_SIZE
    val radialBasisLayer = HyperRadialBasisLayer(inputSize, neuronSize)

    override fun retrain(dataset: Dataset, epoch: Int, learningRate: Double) {
        init(dataset)
        train(dataset, epoch, learningRate)
    }

    override fun train(dataset: Dataset, epoch: Int, learningRate: Double) {
        initializeIfNeed(dataset)
        logger?.log("\nSTART TRAIN\n")
        learningStrategy.train(this, normalizer.normalizedDataset!!, epoch, learningRate, errorDrawer)
    }

    override fun test(dataset: Dataset, accuracyDelta: Double): Float {
        var errors = 0
        logger?.log("\nSTART TESTING")
        dataset.rows.forEach { row ->
            logger?.log("\n$row")
            val output = calculate(row.inputs)
            if (output !in (row.output - accuracyDelta)..(row.output + accuracyDelta)) {
                errors++
            }
            logger?.log("test: real = ${row.output} network = $output")
        }
        logger?.log("errors = $errors accuracy = ${1f - errors.toFloat() / dataset.rows.size}")
        return 1f - errors.toFloat() / dataset.rows.size
    }

    override fun calculate(inputs: List<Double>): Double {
        require(inputs.size == inputSize)
        val normalizedInputs = normalizer.normalization(inputs)
        val output = radialBasisLayer.calculate(normalizedInputs)
        return normalizer.originalValue(output)
    }

    private fun initializeIfNeed(dataset: Dataset) {
        if (normalizer.dataset == null) {
            init(dataset)
        }
    }

    private fun init(dataset: Dataset) {
        normalizer.dataset = dataset
        radialBasisLayer.init(normalizer.normalizedDataset!!)
    }

    companion object {
        private const val OUTPUT_SIZE = 1
    }
}