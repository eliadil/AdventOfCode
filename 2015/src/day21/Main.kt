package day21

/**
 * Created by eliadil on 2015-12-21.
 */
/*
val monsterHP = 12
val monsterAtt = 7
val monsterDef = 2
*/
val monsterHP = 100
val monsterAtt = 8
val monsterDef = 2


fun main(args: Array<String>) {

    val wepCost = listOf(8, 10, 25, 40, 74)
    val wepDmg = listOf(4, 5, 6, 7, 8)

    val armCost = listOf(0, 13, 31, 53, 75, 102)
    val armArm = listOf(0, 1, 2, 3, 4, 5)

    val ringtCost = listOf(0, 25, 50, 100, 20, 40, 80)

    var min = 1000000000;
    var max = 0;
    for (i in 0..4)
        for (j in 0..5)
            for (k in 0..6)
                for (l in 0..6)
                    if (k != l || k + l == 0) {
                        var totalAtt = wepDmg[i]
                        totalAtt += if(k in 1..3) k else 0
                        totalAtt += if(l in 1..3) l else 0

                        var totalDef = armArm[j]
                        totalDef += if(k in 4..6) k -3 else 0
                        totalDef += if(l in 4..6) l-3 else 0

                        val cost = wepCost[i] + armCost[j] + ringtCost[k] + ringtCost[l]
                        if (didIWin(100, totalAtt, totalDef)) {
                            if (cost < min )
                                min = cost;


                            if (cost == 91) {
                                println("$i   $j   $k  $l")
                            }
                        } else {
                            if(cost > max)
                                max = cost

                            if (cost == 233) {
                                println("$i   $j   $k  $l")
                            }

                        }
                    }

    println(min)
    println(max)

    println(didIWin(100, 3, 6))
}


fun didIWin(hp: Int, att: Int, def: Int): Boolean {

    val myTotalAtt = if (att - monsterDef < 1) 1 else att - monsterDef
    val monsterTotalAtt = if (monsterAtt - def < 1) 1 else monsterAtt - def

    val myHitsRequired = hitsRequired(monsterHP, myTotalAtt)
    val monsterHitsRequired = hitsRequired(hp, monsterTotalAtt)

    return myHitsRequired <= monsterHitsRequired
}

fun hitsRequired(hp: Int, att: Int): Int {
    return hp / att + if ( hp % att != 0) 1 else 0
}
