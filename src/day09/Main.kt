package day09

import java.io.File
import kotlin.text.Regex

/**
 * Created by eliadil on 2015-12-09.
 */


val listOfCities = listOf<String>("AlphaCentauri", "Arbre", "Faerun", "Norrath", "Snowdin", "Straylight", "Tambi", "Tristram")
val range = 0..listOfCities.size - 1
val twoDimArr = Array(listOfCities.size, { IntArray(listOfCities.size) })

val twoDimArr2 = Array(listOfCities.size, { IntArray(listOfCities.size) })

fun main(args: Array<String>) {

    val lineRegex = Regex("""(\w+) to (\w+) = (\d+)""")

    for (i in range)
        for (j in range)
            twoDimArr[i][j] = Int.MAX_VALUE


    val file = File("resources/day09")
    file.forEachLine { line ->
        val groups = lineRegex.find(line)!!.groups

        val from = groups[1]!!.value
        val to = groups[2]!!.value
        val distance = groups[3]!!.value.toInt()

        val fromId = listOfCities.indexOf(from)
        val toId = listOfCities.indexOf(to)

        twoDimArr[fromId][toId] = distance
        twoDimArr[toId][fromId] = distance
        twoDimArr2[fromId][toId] = distance
        twoDimArr2[toId][fromId] = distance

    }

    var minDistance: Long = 9000000000;
    for (i in range) {
        val dist = visitNext(i, 0, setOf(i))
        if (dist < minDistance)
            minDistance = dist
    }
    println(minDistance)

    var maxDistance: Long = 0;
    for (i in range) {
        val dist = visitNextMax(i, 0, setOf(i))
        if (dist > maxDistance)
            maxDistance = dist
    }
    println(maxDistance)
}

fun visitNext(current: Int, distance: Long, visitedCities: Set<Int>): Long {

    if (visitedCities.size == listOfCities.size) {
        return distance
    }
    var localMin = 9000000000L

    for (j in range) {
        if (j !in visitedCities) {
            val newDist = twoDimArr[current][j] + distance

            val localDist = visitNext(j, newDist, visitedCities.plus(j))

            if (localDist < localMin)
                localMin = localDist
        }
    }
    return localMin
}


fun visitNextMax(current: Int, distance: Long, visitedCities: Set<Int>): Long {

    if (visitedCities.size == listOfCities.size) {
        return distance
    }
    var localMax = 0L

    for (j in range) {
        if (j !in visitedCities) {
            val newDist = twoDimArr2[current][j] + distance

            val localDist = visitNextMax(j, newDist, visitedCities.plus(j))

            if (localDist > localMax)
                localMax = localDist
        }
    }
    return localMax
}