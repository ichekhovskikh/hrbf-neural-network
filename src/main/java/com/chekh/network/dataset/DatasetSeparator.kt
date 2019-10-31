package com.chekh.network.dataset

object DatasetSeparator {

    /**
     * first  - train dataset
     * second - test dataset
     */
    fun separate(dataset: Dataset, trainPercent: Float): Pair<Dataset, Dataset> {
        val trainSize = (dataset.rows.size * trainPercent).toInt()
        if (trainSize >= dataset.rows.size) {
            return dataset to Dataset(emptyList())
        } else if (trainSize <= 0) {
            return Dataset(emptyList()) to dataset
        }
        val rows = dataset.rows.shuffled()
        val trainDataset = Dataset(rows.subList(0, trainSize))
        val testDataset = Dataset(rows.subList(trainSize, rows.size))
        return trainDataset to testDataset
    }
}