package com.chekh.network.log

data class Point(var x: Double, var y: Double)

interface ChartDrawer {
    fun draw(point: Point)
    fun clear()
}