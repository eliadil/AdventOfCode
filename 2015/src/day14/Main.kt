package day14

/**
 * Created by eliadil on 2015-12-15.
 */

val time = 2503

val vixen = Array(132, { i -> if (i == 0) 0 else if (i <= 7) (i) * 19 else 19 * 7 })
val rudolph = Array(44, { i -> if (i == 0) 0 else if (i <= 15) (i) * 3 else 3 * 15 })
val donner = Array(174, { i -> if (i == 0) 0 else if (i <= 9) (i) * 19 else 19 * 9 })
val blitzen = Array(168, { i -> if (i == 0) 0 else if (i <= 9) (i) * 19 else 19 * 9 })
val comet = Array(90, { i -> if (i == 0) 0 else if (i <= 7) (i) * 13 else 13 * 7 })
val cupid = Array(152, { i -> if (i == 0) 0 else if (i <= 6) (i) * 25 else 25 * 6 })
val dasher = Array(42, { i -> if (i == 0) 0 else if (i <= 3) (i) * 14 else 14 * 3 })
val dancer = Array(54, { i -> if (i == 0) 0 else if (i <= 16) (i) * 3 else 3 * 16 })
val prancer = Array(150, { i -> if (i == 0) 0 else if (i <= 6) (i) * 25 else 25 * 6 })


val allReindeer = arrayOf(vixen, rudolph, donner, blitzen, comet, cupid, dasher, dancer, prancer)
fun main(args: Array<String>) {


    println(allReindeer.map { r -> getReindeerDistanceAt(r, time) }.max())


    val scores = IntArray(9)

    for (i in 1..time) {
        val distancesAtTime = allReindeer.map { r -> getReindeerDistanceAt(r, i) }
        val maxAtTime = distancesAtTime.max()

        for (j in 0..8) {
            if (distancesAtTime[j] == maxAtTime)
                scores[j]++
        }

    }


    for (j in 0..8) {
        println(scores[j])
    }
}

fun getReindeerDistanceAt(r: Array<Int>, seconds: Int): Int {
    val size = r.size - 1;

    val fullCourseTimes = seconds / size
    val left = seconds % size

    return fullCourseTimes * r[size] + r[left]
}