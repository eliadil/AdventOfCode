package day08

import java.io.File
import kotlin.text.Regex
import kotlin.text.RegexOption

/**
 * Created by eliadil on 2015-12-08.
 */

fun main(args: Array<String>) {

    var codeLength = 0
    var memoryLength = 0
    var encodedLength = 0

    val slashRegex = Regex("\\\\", RegexOption.LITERAL)
    val quoteRegex = Regex("\\\"", RegexOption.LITERAL)
    val hexRegex = Regex("\\\\x([a-fA-F0-9]){2}")


    val file = File("resources/day08")
    file.forEachLine { line ->
        codeLength += line.length
        memoryLength += line.replace(slashRegex, "S").replace(quoteRegex, "K").replace(hexRegex, "H").length - 2
        encodedLength += line.replace(slashRegex, "SSSS").replace(quoteRegex, "KKKK").replace(hexRegex, "HHHHH").length + 4
    }


    println(codeLength)
    println(memoryLength)
    println(codeLength - memoryLength)

    println(encodedLength)
    println(encodedLength - codeLength)
}