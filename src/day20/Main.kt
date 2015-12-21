package day20

/**
 * Created by eliadil on 2015-12-21.
 */

fun main(args: Array<String>) {

    val input = 33100000

    val inputByTen = input / 10

    //sum of divisors has to be greater than 3m oO

    /*
    println(getDivisors(1000080).sum())
    println()
    for(i in 0..1200000)
    {
        val sumOfDiv = getDivisors(i).sum()
        if(sumOfDiv >= inputByTen) {
            println(i)
            println(sumOfDiv)
            break;
        }
    }
*/

    val MAX = 35000000
    val range = 1..MAX-1

    val longArray = IntArray(MAX)

    for(i in range)
    {
        var k = i;
        while(k < MAX)
        {
            longArray[k] += 10*i
            k += i
        }
    }


    for(i in range)
        if(longArray[i] >= input) {
            println(i)
            break
        }


    val longArray2 = IntArray(MAX)

    for(i in range)
    {
        var k = i;
        var times = 0
        while(k < MAX && times < 50)
        {
            longArray2[k] += 11*i
            k += i
            times++
        }
    }

    for(i in range)
        if(longArray2[i] >= input) {
            println(i)
            break
        }

}

fun getDivisors(num: Int): Set<Int> {


    val top = Math.ceil(Math.sqrt(num + 0.0))

    val range = 1..top.toInt()
    var setto = emptySet<Int>()
    for(i in range)
    {
        if(num % i == 0) {
            setto = setto.plus(i)
            setto = setto.plus(num / i)
        }
    }
    return setto
}