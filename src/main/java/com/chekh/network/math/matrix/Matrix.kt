package com.chekh.network.math.matrix

internal data class Matrix(private var matrix: MutableList<MutableList<Double>>) {
    val rowSize = matrix.size
    val columnSize = matrix.first().size

    constructor(rows: Int, columns: Int) : this(MutableList(rows) { MutableList(columns) { 0.0 } })

    operator fun get(row: Int, column: Int) = matrix[row][column]

    operator fun set(row: Int, column: Int, element: Double) {
        matrix[row][column] = element
    }

    operator fun times(otherMatrix: Matrix): Matrix {
        val result = Matrix(rowSize, otherMatrix.columnSize)
        for (i in 0 until rowSize) {
            for (j in 0 until otherMatrix.columnSize) {
                for (k in 0 until otherMatrix.rowSize) {
                    result[i, j] += this[i, k] * otherMatrix[k, j]
                }
            }
        }
        return result
    }

    fun trans(): Matrix {
        val result = Matrix(columnSize, rowSize)
        for (i in 0 until rowSize) {
            for (j in 0 until columnSize) {
                result[j, i] = this[i, j]
            }
        }
        return result
    }

    fun single() = matrix.first().first()

    fun toList(row: Int) = matrix[row]

    fun toList() = matrix

    companion object {
        fun List<Double>.toMatrix() = Matrix(MutableList(1) { this.toMutableList() })
    }
}