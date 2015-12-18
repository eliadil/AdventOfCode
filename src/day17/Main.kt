package day17

/**
 * Created by eliadil on 2015-12-17.
 */

var count = 0
var minimalContainersUsed = 20;
var countFour = 0;
val containerVolumes = intArrayOf(50, 48, 45, 44, 42, 41, 35, 35, 33, 30, 24, 20, 18, 18, 16, 14, 13, 13, 6, 1)
val containers = containerVolumes.mapIndexed { i, ele -> Container(i, ele) }


fun main(args: Array<String>) {


    val volume = 150
    findCombinations(volume, emptyList<Int>(), 0)
    println(count)
    println(minimalContainersUsed)
    println(countFour)
}

fun findCombinations(volumeLeft: Int, usedContainers: List<Int>, index: Int) {

    if (volumeLeft == 0) {
        count++
        if(usedContainers.size < minimalContainersUsed)
            minimalContainersUsed = usedContainers.size
        if(usedContainers.size == 4)
            countFour++
    } else {
        if (containers.subList(index, containers.size).sumBy { x -> x.volume } < volumeLeft )
            return
        for (i in index..containers.size - 1) {
            val container = containers[i]

            if (volumeLeft >= container.volume ) {
                findCombinations(volumeLeft - container.volume, usedContainers.plus(container.id), i + 1)
            }
        }
    }

}
