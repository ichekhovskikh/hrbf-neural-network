package com.chekh.network.dataset

interface DatasetNormalizer {
    var dataset: Dataset?

    val normalizedDataset: Dataset?

    fun normalization(values: List<Double>): List<Double>

    fun originalValue(normalizedValue: Double): Double
}