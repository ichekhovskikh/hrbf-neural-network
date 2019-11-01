package com.chekh.network.dataset

class SimpleDatasetNormalizer(override var dataset: Dataset? = null) : DatasetNormalizer {

    override val normalizedDataset: Dataset?
        get() = if (dataset != null) normalization(dataset!!) else dataset

    override fun normalization(values: List<Double>): List<Double> {
        if (dataset == null || dataset?.rows?.isEmpty() == true) {
            return values
        }
        val (min, max) = findMinMax(dataset!!)
        return values.map { (it - min) / (max - min) }
    }

    override fun originalValue(normalizedValue: Double): Double {
        if (dataset == null || dataset?.rows?.isEmpty() == true) {
            return normalizedValue
        }
        val (min, max) = findMinMax(dataset!!)
        return normalizedValue * (max - min) + min
    }

    private fun normalization(dataset: Dataset): Dataset {
        if (dataset.rows.isEmpty()) {
            return dataset
        }
        val (min, max) = findMinMax(dataset)
        val length = max - min
        val normalizedRows = dataset.rows.map {
            Row(
                inputs = it.inputs.map { input -> (input - min) / length },
                output = (it.output - min) / length
            )
        }
        return Dataset(normalizedRows)
    }

    private fun findMinMax(dataset: Dataset): Pair<Double, Double> {
        var min = dataset.rows.first().output
        var max = min
        dataset.rows.forEach {
            val minInput = it.inputs.min()!!
            val maxInput = it.inputs.max()!!
            min = if (min > it.output) it.output else min
            min = if (min > minInput) minInput else min
            max = if (max < it.output) it.output else max
            max = if (max < maxInput) maxInput else max
        }
        return min to max
    }
}