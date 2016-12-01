package day07

import java.io.File
import kotlin.text.Regex

/**
 * Created by eliadil on 2015-12-07.
 */

fun main(args: Array<String>) {

    val start = System.currentTimeMillis()
    var opResultList: List<Pair<Operation, String>> = emptyList()
    val file = File("resources/day07-02")
    file.forEachLine { line ->
        val parts = line.split(" -> ")
        val key = parts[1]
        val opString = parts[0]
        var op: Operation? = null


        if (opString.startsWith("NOT")) {
            val innerParts = opString.split(" ")
            op = NotOperation(innerParts[1])
        } else if (opString.contains("AND")) {
            val innerParts = opString.split(" ")
            var left: Any = innerParts[0]
            var right: Any = innerParts[2]

            if (innerParts[0].matches(Regex("[0-9]+")))
                left = innerParts[0].toInt()
            if (innerParts[2].matches(Regex("[0-9]+")))
                right = innerParts[2].toInt()

            op = AndOperation(left, right)
        } else if (opString.contains("OR")) {
            val innerParts = opString.split(" ")
            var left: Any = innerParts[0]
            var right: Any = innerParts[2]

            if (innerParts[0].matches(Regex("[0-9]+")))
                left = innerParts[0].toInt()
            if (innerParts[2].matches(Regex("[0-9]+")))
                right = innerParts[2].toInt()

            op = OrOperation(left, right)
        } else if (opString.contains("SHIFT")) {
            val innerParts = opString.split(" ")
            var left = innerParts[0]
            var right: Int = 0
            if (innerParts[2].matches(Regex("[0-9]+")))
                right = innerParts[2].toInt()

            if (innerParts[1].startsWith("L"))
                op = LShiftOperation(left, right)
            else
                op = RShiftOperation(left, right)
        }
        else {
            if(opString.matches(Regex("[0-9]+")))
                op = AssignmentOperation(opString.toInt())
            else
                op = AssignmentOperation(opString)
        }

        opResultList = opResultList.plus(Pair(op, key))
    }


    println(opResultList.size)
    var values: Map<String, Int> = emptyMap()

    while(opResultList.size != 0) {
        val partition = opResultList.partition { opPair -> opPair.first.canEvaluate(values) }
        partition.first.forEach { opValPair ->
            val value = opValPair.first.evaluate(values)
            values = values.plus(Pair(opValPair.second, value))
        }
        opResultList = partition.second;
    }

    println(values["a"])
    println(System.currentTimeMillis() - start)

}
