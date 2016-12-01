package day11

/**
 * Created by eliadil on 2015-12-11.
 */

val input = "cqjxxzaa"
val MAX = ('z' - 'a').toInt()
val pass = input.toCharList().map { c -> (c - 'a').toInt() }.toIntArray()

fun main(args: Array<String>) {

    printPass()

    while (!isValid()) {
        nextPass()
    }
    for (i in 0..7)
        print('a' + pass[i])
    println()
}

fun printPass() {
    for (i in 0..7) {
        print(pass[i])
        print(" ")
    }
    println()
}

fun nextPass() {

    var carryOver = 1;

    for (i in 7 downTo 0) {
        pass[i] += carryOver
        if (pass[i] > MAX) {
            pass[i] = 0
        } else {
            carryOver = 0
        }
    }
    if (carryOver == 1)
        for (i in 0..7)
            pass[i] = MAX
}

val forbidden = listOf('i'.toInt(), 'o'.toInt(), 'l'.toInt())

fun isValid(): Boolean {
    for (i in 0..7)
        if (forbidden.contains(pass[i]))
            return false

    var foundStairs = false
    for (i in 0..5)
        if (pass[i] + 1 == pass[i+1] && pass[i + 2] - 1 == pass[i + 1])
            foundStairs = true
    if (!foundStairs)
        return false

    var pairsFound = 0
    var justFound = false
    for (i in 0..6) {

        if (pass[i] == pass[i + 1] && !justFound) {
            pairsFound++
            justFound = true
        } else {
            justFound = false
        }
    }

    return pairsFound >= 2
}