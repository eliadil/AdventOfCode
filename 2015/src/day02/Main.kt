package day02

import java.io.File
import kotlin.text.Regex

/**
 * Created by eliadil on 2015-12-02.
 */

fun main(args: Array<String>) {
    val boxes = getBoxes();
    val sum = boxes.fold(0, {acc, box ->
        acc + box.getSmallestSideArea() + box.getTotalArea()
    });
    println(sum)

    val ribbonSum = boxes.fold(0, { acc, box ->
        acc + box.getSmallestSidePerimeter() + box.getVolume()
    })
    println(ribbonSum)
}


fun getBoxes(): List<Box> {
    val file = File("resources/day02")
    val boxes = file.readLines().map { line ->
        val regex = Regex("(\\d+)x(\\d+)x(\\d+)");

        val groups = regex.matchEntire(line)?.groups!!

        val length = groups[1]!!.value.toInt();
        val width = groups[2]!!.value.toInt();
        val height = groups[3]!!.value.toInt();

        Box(length, width, height)
    }
    return boxes;
}