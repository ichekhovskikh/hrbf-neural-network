package com.chekh.network.learning

import com.chekh.network.HyperRadialBasisNeuralNetwork
import com.chekh.network.dataset.Dataset
import com.chekh.network.log.ChartDrawer

interface HyperRadialBasisLearningStrategy {
    fun train(
        network: HyperRadialBasisNeuralNetwork,
        dataset: Dataset,
        epoch: Int,
        learningRate: Double,
        errorDrawer: ChartDrawer? = null
    )
}