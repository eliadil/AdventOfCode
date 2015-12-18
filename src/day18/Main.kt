package day18

import java.io.File

/**
 * Created by eliadil on 2015-12-18.
 */
fun main(args: Array<String>) {


    val twoDimArr = Array(100, { IntArray(100) })
    val twoDimArr2 = Array(100, { IntArray(100) })
    var index = 0;

    val file = File("resources/day18")
    file.forEachLine { line ->
        for(i in 0..line.length - 1)
        {
            twoDimArr[index][i] = if (line[i] == '#') 1 else 0
        }
        index++
    }

    val range = 0..99

    twoDimArr[0][0] = 1
    twoDimArr[99][0] = 1
    twoDimArr[0][99] = 1
    twoDimArr[99][99] = 1

    for(i in range)
    {
        for (j in range)
        {
            for(k in range)
            {
                val glowingNeighboursCount = countNeighboursThatAreOn(twoDimArr, j, k)
                if(twoDimArr[j][k] == 1)
                {
                    if(!(glowingNeighboursCount == 2 || glowingNeighboursCount == 3))
                    {
                        twoDimArr2[j][k] = 0
                    }
                    else
                    {
                        twoDimArr2[j][k] = 1
                    }
                }
                else
                {
                    if(glowingNeighboursCount == 3)
                    {
                        twoDimArr2[j][k] = 1
                    }
                    else
                    {
                        twoDimArr2[j][k] = 0
                    }
                }
            }
        }

        twoDimArr2[0][0] = 1
        twoDimArr2[99][0] = 1
        twoDimArr2[0][99] = 1
        twoDimArr2[99][99] = 1


        for(x in range)
            for( z in range)
                twoDimArr[x][z] = twoDimArr2[x][z]
    }


    var count = 0;
    for(i in range) {
        for (j in range) {
            count += twoDimArr[i][j]
        }
    }

    println(count)
}

fun countNeighboursThatAreOn(twoDimArr: Array<IntArray>, x: Int, z: Int): Any {

    var count = 0
    val range = 0..99
    if ((x - 1) in range && (z - 1) in range)
        count += twoDimArr[x-1][z-1]
    if ((x - 1) in range && (z) in range)
        count += twoDimArr[x-1][z]
    if ((x - 1) in range && (z + 1) in range)
        count += twoDimArr[x-1][z+1]

    if ((x) in range && (z - 1) in range)
        count += twoDimArr[x][z-1]
    if ((x) in range && (z + 1) in range)
        count += twoDimArr[x][z+1]

    if ((x + 1) in range && (z - 1) in range)
        count += twoDimArr[x+1][z-1]
    if ((x + 1) in range && (z) in range)
        count += twoDimArr[x+1][z]
    if ((x + 1) in range && (z + 1) in range)
        count += twoDimArr[x+1][z+1]

    return count
}

