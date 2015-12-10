package day10

/**
 * Created by eliadil on 2015-12-10.
 */

fun main(args: Array<String>) {

    val input = "3113322113"
    var out = StringBuilder(input)
    var out2: StringBuilder
    for (i in 1..50) {
        out2 = StringBuilder()
        var what = '-'
        var howMany = 0

        for (c in out.toString()) {
            if (what == '-') {
                what = c;
            }
            if (c != what) {
                out2 = out2.append(howMany).append(what)
                what = c;
                howMany = 1
            } else {
                howMany += 1
            }
        }

        out2 = out2.append(howMany).append(what)
        //println(out2.toString())
        out = out2
    }

    println(out.toString().length)
}