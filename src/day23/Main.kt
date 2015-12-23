package day23

import day08.Reg
import java.io.File
import java.util.*
import kotlin.text.Regex

/**
 * Created by eliadil on 2015-12-23.
 */

val A = Reg(1)
val B = Reg(0)

fun main(args: Array<String>) {

    val triple = { x: Reg -> x.triple() }
    val incr = { x: Reg -> x.incr() }
    val half = { x: Reg -> x.half() }

    val testIfOne = { x: Reg -> x.value == 1 }
    val testIfEven = { x: Reg -> x.value % 2 == 0 }
    val fakeTest = { x: Any -> true }

    val list: ArrayList<Triple<String, Pair<(Reg) -> Any, Any>, Int>> = ArrayList()

    val OP = "op"
    val TS = "ts"

    val file = File("resources/day23")
    file.forEachLine { line ->
        val regex = Regex("(inc|tpl|hlf|jio|jie|jmp) (a|b|[\\+-]\\d+)(?:, ([\\+-]\\d+))?")
        val result = regex.matchEntire(line)
        val groups = result!!.groups

        val operation = groups[1]!!.value


        val arg =
                if (groups[2]!!.value.equals("a"))
                    A
                else
                    B

        val jump =
                if (operation.equals("jie") || operation.equals("jio"))
                    groups[3]!!.value.toInt()
                else if (operation.equals("jmp"))
                    groups[2]!!.value.toInt()
                else 1
        if (operation.equals("inc"))
            list.add(Triple(OP, Pair(incr, arg), jump))
        if (operation.equals("hlf"))
            list.add(Triple(OP, Pair(half, arg), jump))
        if (operation.equals("tpl"))
            list.add(Triple(OP, Pair(triple, arg), jump))

        if (operation.equals("jmp"))
            list.add(Triple(TS, Pair(fakeTest, arg), jump))

        if (operation.equals("jie"))
            list.add(Triple(TS, Pair(testIfEven, arg), jump))
        if (operation.equals("jio"))
            list.add(Triple(TS, Pair(testIfOne, arg), jump))
    }

    var i = 0;
    while(i < list.size)
    {
        val line = list[i]
        if(line.first.equals(OP))
        {
            line.second.first.invoke(line.second.second as Reg)
            i += line.third
        }
        else
        {
            if(line.second.first.invoke(line.second.second as Reg) as Boolean)
            {
                i += line.third
            }
            else
            {
                i += 1
            }
        }
    }

    println(A.value)
    print(B.value)
}
