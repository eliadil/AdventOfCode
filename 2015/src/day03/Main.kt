package day03

import java.io.File

/**
 * Created by eliadil on 2015-12-03.
 */

fun main(args: Array<String>) {

    val directions = getDirections()
    val position :Pair<Int, Int> = Pair(0,0)

    val setto = directions.fold(Pair(setOf(position), position), {acc, direction ->
        val newPos = move(acc.second, direction)
        Pair(acc.first.plus(newPos), newPos)
    })

    println(setto.first.size)

    val rangeNormal = IntRange(0, directions.length - 1).step(2);

    val normalSantaDirections = directions.slice(rangeNormal)
    val rangeRobot = IntRange(1, directions.length - 1).step(2);

    val robotSantaDirections = directions.slice(rangeRobot)

    val normalSetto = normalSantaDirections.fold(Pair(setOf(position), position), {acc, direction ->
        val newPos = move(acc.second, direction)
        Pair(acc.first.plus(newPos), newPos)
    })

    val bothSetto = robotSantaDirections.fold(Pair(normalSetto.first, position), {acc, direction ->
        val newPos = move(acc.second, direction)
        Pair(acc.first.plus(newPos), newPos)
    })


    println(bothSetto.first.size)
}

private fun move(position : Pair<Int, Int>, direction: Char): Pair<Int, Int>
{
    var horiz: Int = 0
    var vert: Int = 0
    if(direction == 'v')
    {
        horiz = 0
        vert = -1
    }
    if(direction == '^')
    {
        horiz = 0
        vert = 1
    }
    if(direction == '<')
    {
        horiz = -1
        vert = 0
    }
    if(direction == '>')
    {
        horiz = 1
        vert = 0
    }

    return Pair(position.first + horiz, position.second + vert)
}


fun getDirections(): String {
    val file = File("resources/day03")
    return file.readText();
}