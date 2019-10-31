package com.chekh.network.io

import java.io.FileReader
import java.io.BufferedReader
import java.io.IOException

@Throws(IOException::class)
fun readCsv(path: String): List<List<Double>> {
    val list = mutableListOf<List<Double>>()
    val divider = ","
    var csvReader: BufferedReader? = null
    try {
        csvReader = BufferedReader(FileReader(path))
        var line = csvReader.readLine()
        while (line != null) {
            list.add(line.split(divider).map { it.toDouble() })
            line = csvReader.readLine()
        }
    } catch (e: Exception) {
        throw e
    } finally {
        try {
            csvReader?.close()
        } catch (e: IOException) {
            throw e
        }
    }
    return list
}