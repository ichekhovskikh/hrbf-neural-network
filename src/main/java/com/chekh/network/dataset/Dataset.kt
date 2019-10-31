package com.chekh.network.dataset

import com.chekh.network.io.readCsv

data class Row(var inputs: List<Double>, var output: Double)

data class Dataset(var rows: List<Row>) {

    constructor(path: String) : this(readRows(path))

    val inputSize = rows.firstOrNull()?.inputs?.size ?: 0

    fun getInputParams(inputIndex: Int) = rows.map { it.inputs[inputIndex] }

    companion object {
        private fun readRows(path: String): List<Row> {
            val rows = mutableListOf<Row>()
            readCsv(path).forEach {
                val (inputs, output) = divideInputsAndOutput(it)
                rows.add(Row(inputs, output))
            }
            return rows
        }

        private fun divideInputsAndOutput(row: List<Double>): Pair<List<Double>, Double> {
            val inputs = row.subList(0, row.size - 1)
            val output = row[row.size - 1]
            return Pair(inputs, output)
        }
    }
}