package day19

import java.io.File
import kotlin.text.Regex

/**
 * Created by eliadil on 2015-12-21.
 */
var transformations = emptyList<Pair<String, String>>()
var baseString = ""

fun main(args: Array<String>) {

    val file = File("resources/day19")
    val regex = Regex("([a-zA-Z]*) => ([a-zA-Z]*)")
    file.forEachLine { line ->


        if ( line.matches(regex) ) {
            val result = regex.find(line)
            val left = result!!.groups[1]!!.value
            val right = result!!.groups[2]!!.value
            transformations = transformations.plus(Pair(left, right))
        } else if (line.length > 0) {
            baseString = line
        }
    }

    var set = emptySet<String>()

    transformations.forEach { x ->

        val key = x.first
        val value = x.second

        var found = true
        var indexStart = 0
        while (found) {
            val indexOfX = baseString.indexOf(key, indexStart)
            indexStart = indexOfX + 1
            if (indexOfX != -1) {
                set = set.plus(baseString.substring(0, indexOfX) + value + baseString.substring(indexOfX + key.length, baseString.length))
            } else {
                found = false
            }
        }
    }
    println(set.size)


    val fromBase = "e"

    //goForward(fromBase, 0)
    goBackwards(baseString, 0)
}

var wootSet = emptySet<String>()

fun goForward(input: String, transDone: Int) {

    if (input.equals(baseString)) {
        print(transDone)
    } else if (input.length < baseString.length) {
        transformations.forEach { x ->

            val key = x.first
            val value = x.second

            var found = true
            var indexStart = 0
            while (found) {
                val indexOfX = input.indexOf(key, indexStart)
                indexStart = indexOfX + 1
                if (indexOfX != -1) {
                    val newInput = input.substring(0, indexOfX) + value + input.substring(indexOfX + key.length, input.length)
                    if (!wootSet.contains(newInput)) {
                        wootSet = wootSet.plus(newInput)
                        goForward(newInput, transDone + 1)
                    }
                } else {
                    found = false
                }
            }
        }
    }
}


fun goBackwards(input: String, transDone: Int) {
    if (input.equals("e"))
        println(transDone)
    else {
        transformations.forEach { x ->

            val key = x.second
            val value = x.first

            var found = true
            var indexStart = 0
            while (found) {
                val indexOfX = input.indexOf(key, indexStart)
                indexStart = indexOfX + 1
                if (indexOfX != -1) {
                    val newInput = input.substring(0, indexOfX) + value + input.substring(indexOfX + key.length, input.length)
                    goBackwards(newInput, transDone + 1)
                } else {
                    found = false
                }
            }
        }
    }
}