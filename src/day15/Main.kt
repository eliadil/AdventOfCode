package day15

/**
 * Created by eliadil on 2015-12-15.
 */
    val cookieValArr = Array(4, { Array (5, { IntArray(101) }) })
fun main(args: Array<String>) {




    cookieValArr[0][0][1] = 2
    cookieValArr[0][2][1] = -2
    cookieValArr[0][4][1] = 3

    cookieValArr[1][1][1] = 5
    cookieValArr[1][2][1] = -3
    cookieValArr[1][4][1] = 3

    cookieValArr[2][2][1] = 5
    cookieValArr[2][3][1] = -1
    cookieValArr[2][4][1] = 8

    cookieValArr[3][1][1] = -1
    cookieValArr[3][3][1] = 5
    cookieValArr[3][4][1] = 8

    val range = 0..100

    for(cookie in 0..3)
    {
        for(property in 0..4)
        {
            for(times in range)
            {
                cookieValArr[cookie][property][times] = cookieValArr[cookie][property][1] * times
            }
        }
    }


    var max = 0L;
    var maxCalorie500 = 0L;

    for (i in range)
        for (j in range)
            for (k in range)
                for (l in range)
                    if (i + j + k + l == 100)
                    {
                        val cookieTotal = calculateCookieTotal(i,j,k,l);
                        if(cookieTotal > max) {
                            max = cookieTotal
                            println("$i $j $k $l")
                        }

                        val cookieTotalCalorie = calculateCookieTotalCalorie(i,j,k,l);
                        if(cookieTotalCalorie > maxCalorie500)
                            maxCalorie500 = cookieTotalCalorie
                    }


    println(max)
    println(maxCalorie500)

}

fun calculateCookieTotal(i: Int, j: Int, k: Int, l: Int): Long
{
    val arrOfTotalProps = IntArray(5)
    val arrOfAmounts = intArrayOf(i,j,k,l)

    for(xx in 0..3)
        for(zz in 0..4)
            arrOfTotalProps[zz] += cookieValArr[xx][zz][arrOfAmounts[xx]]


    for(zz in 0..4)
        if(arrOfTotalProps[zz] < 0)
            arrOfTotalProps[zz] = 0
    //val totalValue :Long = arrOfTotalProps.fold(1L, {total, next -> next * total})
    val totalValue2 :Long = arrOfTotalProps[0].toLong() * arrOfTotalProps[1].toLong() *  arrOfTotalProps[2].toLong() *arrOfTotalProps[3].toLong()
    return totalValue2

}

fun calculateCookieTotalCalorie(i: Int, j: Int, k: Int, l: Int): Long
{
    val arrOfTotalProps = IntArray(5)
    val arrOfAmounts = intArrayOf(i,j,k,l)

    for(xx in 0..3)
        for(zz in 0..4)
            arrOfTotalProps[zz] += cookieValArr[xx][zz][arrOfAmounts[xx]]


    for(zz in 0..4)
        if(arrOfTotalProps[zz] < 0)
            arrOfTotalProps[zz] = 0
    if(arrOfTotalProps[4] != 500)
        return 0L

    val totalValue2 :Long = arrOfTotalProps[0].toLong() * arrOfTotalProps[1].toLong() *  arrOfTotalProps[2].toLong() *arrOfTotalProps[3].toLong()
    return totalValue2

}