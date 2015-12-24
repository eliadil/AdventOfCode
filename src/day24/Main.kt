package day24

import day17.Container
import java.util.*

/**
 * Created by eliadil on 2015-12-24.
 */


val containerVolumes = intArrayOf(1, 2, 3, 5, 7, 13, 17, 19, 23, 29, 31, 37, 41, 43, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113)
val containers = containerVolumes.mapIndexed { i, ele -> Container(i, ele) }
var listOfStuff  = ArrayList<List<Int>>()

fun main(args: Array<String>) {


    val volume = containerVolumes.sum() / 4
    findCombinations2(volume, emptyList<Int>(), 0)

    listOfStuff.sort { list, otherList -> list.size.compareTo(otherList.size) }

    println(listOfStuff[0].size)

    var i = 0
    var min = Long.MAX_VALUE
    while(listOfStuff[i].size == 4)
    {
        var woo = 1L

        for(j in 0..3)
            woo *= containers[listOfStuff[i][j]].volume


        if(woo < min)
            min = woo;
        i++
    }
    println(min)
}

fun findCombinations2(volumeLeft: Int, usedContainers: List<Int>, index: Int) {

    if (volumeLeft == 0) {
        listOfStuff.add(usedContainers)
       // println(usedContainers)
    } else {
        if (containers.subList(index, containers.size).sumBy { x -> x.volume } < volumeLeft )
            return
        for (i in index..containers.size - 1) {
            val container = containers[i]

            if (volumeLeft >= container.volume ) {
                findCombinations2(volumeLeft - container.volume, usedContainers.plus(container.id), i + 1)
            }
        }
    }

}