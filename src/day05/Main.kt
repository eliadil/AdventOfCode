package day05

import java.io.File


/**
 * Created by Ginaw on 05.12.2015.
 */


fun main(args: Array<String>) {

    val file = File("resources/day05")
    println(file.readLines().filter { line -> isNice(line) }.size)
    println(file.readLines().filter { line -> betterIsNice(line) }.size)

}


private fun isNice(line: String): Boolean {
    val forbidden = sequenceOf("ab", "cd", "pq", "xy")
    var setOfVowels = emptyList<Char>()
    var double = false
    var prev = line[0]
    if (prev in "aeiou") {
        setOfVowels = setOfVowels.plus(prev)
    }
    for (i in 1..line.length - 1) {
        val curr = line[i]
        if (curr in "aeiou") {
            setOfVowels = setOfVowels.plus(curr)
        }
        if (curr == prev)
            double = true;

        if ("$prev$curr" in forbidden)
            return false

        prev = curr
    }

    return double && setOfVowels.size > 2
}

private fun betterIsNice(line: String): Boolean {
    var pairs = emptyArray<String>()

    var foundSameAroundOne = false

    for (i in 1..line.length - 2) {
        if (line[i - 1] == line[i + 1]) {
            foundSameAroundOne = true;
            break
        }
    }

    if (!foundSameAroundOne)
        return false

    for (i in 0..line.length - 2) {
        pairs = pairs.plus(line.substring(i, i + 2))
    }


    var foundRepeatingPair = false

    for (j in 0..pairs.size - 1) {
        val pair = pairs[j]

        if (j + 2 <= pairs.size - 1 ) {
            for (k in j + 2..pairs.size - 1)
                if (pairs[k] == pair) {
                    foundRepeatingPair = true
                    break
                }
        }
    }
    return foundRepeatingPair
}