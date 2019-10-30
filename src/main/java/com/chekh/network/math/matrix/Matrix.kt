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
        val result = MutableList(matrix.size) { MutableList(otherMatrix.matrix.first().size) { 0.0 } }
        for (i in matrix.indices) {
            for (j in otherMatrix.matrix.first().indices) {
                for (k in otherMatrix.matrix.indices) {
                    result[i][j] += matrix[i][k] * otherMatrix.matrix[k][j]
                }
            }
        }
        return Matrix(result)
    }

    fun trans(): Matrix {
        val result = MutableList(matrix.first().size) { MutableList(matrix.size) { 0.0 } }
        for (i in matrix.indices) {
            for (j in matrix.first().indices) {
                result[j][i] = matrix[i][j]
            }
        }
        return Matrix(result)
    }

    fun single() = matrix.first().first()

    fun toList(row: Int) = matrix[row]

    fun toList() = matrix

    companion object {
        fun List<Double>.toMatrix() = Matrix(MutableList(1) { this.toMutableList() })
    }
}