package com.chekh.network

import com.chekh.network.dataset.DatasetNormalizer
import com.chekh.network.dataset.SimpleDatasetNormalizer
import com.chekh.network.learning.BackPropagationLearningStrategy
import com.chekh.network.learning.HyperRadialBasisLearningStrategy
import com.chekh.network.log.ChartDrawer
import com.chekh.network.log.Logger

class HyperRadialBasisNeuralNetworkBuilder {
    private var inputSize = 1
    private var neuronSize = 1
    private var learningStrategy: HyperRadialBasisLearningStrategy = BackPropagationLearningStrategy()
    private var normalizer: DatasetNormalizer = SimpleDatasetNormalizer()
    private var logger: Logger? = null
    private var errorDrawer: ChartDrawer? = null

    fun withInputSize(inputSize: Int): HyperRadialBasisNeuralNetworkBuilder = apply { this.inputSize = inputSize }

    fun withNeuronSize(neuronSize: Int): HyperRadialBasisNeuralNetworkBuilder = apply { this.neuronSize = neuronSize }

    fun withLearningStrategy(learningStrategy: HyperRadialBasisLearningStrategy): HyperRadialBasisNeuralNetworkBuilder =
        apply {
            this.learningStrategy = learningStrategy
        }

    fun withDatasetNormalizer(normalizer: DatasetNormalizer): HyperRadialBasisNeuralNetworkBuilder =
        apply {
            this.normalizer = normalizer
        }

    fun withLogger(logger: Logger): HyperRadialBasisNeuralNetworkBuilder = apply { this.logger = logger }

    fun withErrorDrawer(drawer: ChartDrawer): HyperRadialBasisNeuralNetworkBuilder = apply { this.errorDrawer = drawer }

    fun build(): HyperRadialBasisNeuralNetwork {
        require(inputSize > 0 && neuronSize > 0)
        return HyperRadialBasisNeuralNetwork(inputSize, neuronSize, learningStrategy, normalizer, logger, errorDrawer)
    }
}