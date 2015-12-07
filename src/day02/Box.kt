package day02

/**
 * Created by eliadil on 2015-12-03.
 */
class Box(val length: Int, val width: Int, val height: Int) {
    fun getTotalArea(): Int {
        return 2*(length * width + length * height + width * height)
    }

    fun getSmallestSideArea(): Int {
        return listOf(length * width, length * height, width * height).minBy { x -> x }!!
    }

    fun getSmallestSidePerimeter(): Int {
        return listOf(2*(length + width), 2*(length + height), 2*(height + width)).minBy { x -> x }!!
    }

    fun getVolume(): Int {
        return length * width * height
    }
}