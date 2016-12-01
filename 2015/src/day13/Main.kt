package day13

/**
 * Created by eliadil on 2015-12-15.
 */
fun main(args: Array<String>) {

    //val mappo =  Array(8, { IntArray(8)})
    val mappo =  Array(9, { IntArray(9)})
    mappo[0][1] = 2
    mappo[0][2] = 26
    mappo[0][3] = -82
    mappo[0][4] = -75
    mappo[0][5] = 42
    mappo[0][6] = 38
    mappo[0][7] = 39
    mappo[1][0] = 40
    mappo[1][2] = -61
    mappo[1][3] = -15
    mappo[1][4] = 63
    mappo[1][5] = 41
    mappo[1][6] = 30
    mappo[1][7] = 87
    mappo[2][0] = -35
    mappo[2][1] = -99
    mappo[2][3] = -51
    mappo[2][4] = 95
    mappo[2][5] = 90
    mappo[2][6] = -16
    mappo[2][7] = 94
    mappo[3][0] = 36
    mappo[3][1] = -18
    mappo[3][2] = -65
    mappo[3][4] = -18
    mappo[3][5] = -22
    mappo[3][6] = 2
    mappo[3][7] = 42
    mappo[4][0] = -65
    mappo[4][1] = 24
    mappo[4][2] = 100
    mappo[4][3] = 51
    mappo[4][5] = 21
    mappo[4][6] = 55
    mappo[4][7] = -44
    mappo[5][0] = -48
    mappo[5][1] = 91
    mappo[5][2] = 8
    mappo[5][3] = -66
    mappo[5][4] = 97
    mappo[5][6] = -9
    mappo[5][7] = -92
    mappo[6][0] = -44
    mappo[6][1] = -25
    mappo[6][2] = 17
    mappo[6][3] = 92
    mappo[6][4] = -92
    mappo[6][5] = 18
    mappo[6][7] = 97
    mappo[7][0] = 92
    mappo[7][1] = -96
    mappo[7][2] = -51
    mappo[7][3] = -81
    mappo[7][4] = 31
    mappo[7][5] = -73
    mappo[7][6] = -89

    //Adding myself

    mappo[0][8] = 0
    mappo[1][8] = 0
    mappo[2][8] = 0
    mappo[3][8] = 0
    mappo[4][8] = 0
    mappo[5][8] = 0
    mappo[6][8] = 0
    mappo[7][8] = 0

    mappo[8][7] = 0
    mappo[8][6] = 0
    mappo[8][5] = 0
    mappo[8][4] = 0
    mappo[8][3] = 0
    mappo[8][2] = 0
    mappo[8][1] = 0
    mappo[8][0] = 0


    val people = arrayOf("Alice", "Bob", "Carol", "David", "Eric", "Frank", "George", "Mallory")

    val listOfUnseatedPeople = listOf(0,1,2,3,4,5,6,7)

    val funToRun = {x : List<Int> ->

        var sum = mappo[x[0]][x[x.size-1]] + mappo[x[x.size-1]][x[0]]
        for(i in 0..x.size - 2)
        {
            sum += mappo[x[i]][x[i+1]] + mappo[x[i+1]][x[i]]
        }

        if (sum > max)
            max = sum

    }

    runPermutations(listOf(8), listOfUnseatedPeople, funToRun)

    println(max)
}
var max = 0;
fun runPermutations(alreadySeaten: List<Int>, listOfUnseatedPeople: List<Int>, funToRun: (List<Int>) -> Unit) {
    if(listOfUnseatedPeople.isEmpty())
    {
        funToRun.invoke(alreadySeaten)
    }
    else
    {
        for (person in listOfUnseatedPeople)
            runPermutations(alreadySeaten.plus(person), listOfUnseatedPeople.minus(person), funToRun)
    }
}
