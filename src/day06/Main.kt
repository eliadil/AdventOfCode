package day06

import java.io.File
import kotlin.text.Regex

/**
 * Created by eliadil on 2015-12-07.
 */

fun main(args: Array<String>) {


    val twoDimArr = Array(1000, { IntArray(1000) })
    for(i in 0..999)
        for(j in 0..999)
            twoDimArr[i][j] = 0

    val file = File("resources/day06")
    file.forEachLine { line ->
        val regex = Regex("(toggle|turn on|turn off) (\\d+),(\\d+) through (\\d+),(\\d+)")
        val groups = regex.find(line)!!.groups

        val operation = groups[1]!!.value

        val opFun = when (operation) {
            "toggle" ->
                { x: Int -> x + 2 }
            "turn off" ->
                { x: Int -> if(x > 1) x - 1 else 0 }
            else ->
                { x: Int -> x + 1 }
        }

        val fromX = groups[2]!!.value.toInt()
        val fromY = groups[3]!!.value.toInt()
        val toX = groups[4]!!.value.toInt()
        val toY = groups[5]!!.value.toInt()

        for(i in fromX..toX)
            for(j in fromY..toY)
                twoDimArr[i][j] = twoDimArr[i][j].let(opFun)
    }
    var sumOn = 0;
    for(i in 0..999)
        for(j in 0..999)
            sumOn += twoDimArr[i][j]
    println(sumOn)
}