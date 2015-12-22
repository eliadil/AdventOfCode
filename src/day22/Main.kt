package day22

/**
 * Created by eliadil on 2015-12-22.
 */
val partTwo = false
val spells = mapOf(
        Pair(Effect.MAGIC_MISSILE, 53),
        Pair(Effect.DRAIN, 73),
        Pair(Effect.SHIELD, 113),
        Pair(Effect.POISON, 173),
        Pair(Effect.RECHARGE, 229)
)
var min = Int.MAX_VALUE

fun main(args: Array<String>) {

    var human = Triple(50, 500, 0)
    //var monster = Pair(71, 10)
    //var monster = Pair(51, 9)
    var monster = Pair(58, 9)

//        var human = Triple(10, 250, 0)
//        var monster = Pair(14, 8)



    var activeEffects = emptyList<Pair<Effect, Int>>()

    round(human, monster, activeEffects, 0, 1, true)
    println(min)

}

//
//(hp, mana, def) human
//(hp, att) monster
//
fun round(human: Triple<Int, Int, Int>, monster: Pair<Int, Int>, activeEffects: List<Pair<Effect, Int>>, manaUsed: Int, round: Int, humanMoves: Boolean) {
    var newHuman: Triple<Int, Int, Int> = Triple(human.first, human.second, 0)
    var newMonster: Pair<Int, Int> = monster

    if(manaUsed > min)
        return

    if(humanMoves) {
        //println("-- Round $round --")
        //println("  -- Player turn --")
    }
    else {
        //println("  -- Boss turn --")
    }

    //println("  $human")
    //println("  $monster")




    //Apply effects
    activeEffects.forEach { x ->
        when (x.first) {
            Effect.SHIELD -> newHuman = Triple(newHuman.first, newHuman.second, 7)
            Effect.POISON -> newMonster = Pair(newMonster.first - 3, newMonster.second)
            Effect.RECHARGE -> newHuman = Triple(newHuman.first, newHuman.second + 101, newHuman.third)
        }
    }
    var newActiveEffects = activeEffects.map { x -> Pair(x.first, x.second - 1) }.filter { x -> x.second > 0 }

    if (newMonster.first <= 0) {
        //println("Monster dies!")
        if (manaUsed < min)
            min = manaUsed
        return
    }

    if (humanMoves) {

        if(partTwo)
        {
            newHuman = Triple(newHuman.first -1, newHuman.second, newHuman.third)
            if(newHuman.first <= 0)
            {
                //println("player dies, woops")
                return
            }
        }

        val spellsAvailable = spells.filter { spell -> newActiveEffects.find { actEffPair -> actEffPair.first == spell.key } == null }
                .filter { spell -> newHuman.second >= spell.value }
        if (spellsAvailable.size == 0) {
            //println("  No spells to cast! ")
            //println("  Player dies out of exhaustion.")
            //println()
            return
        }

        spellsAvailable.forEach { spell ->
            //Cast a spell
            //println("  Player casts a spell! $spell")
            var spellHuman = Triple(newHuman.first, newHuman.second - spell.value, newHuman.third)
            var spellMonster = Pair(newMonster.first, newMonster.second)

            var copyActiveEffects = newActiveEffects.map { pairr -> Pair(pairr.first, pairr.second) }

            when (spell.key) {
                Effect.MAGIC_MISSILE -> spellMonster = Pair(spellMonster.first - 4, spellMonster.second)
                Effect.DRAIN -> {
                    spellMonster = Pair(spellMonster.first - 2, spellMonster.second)
                    spellHuman = Triple(spellHuman.first + 2, spellHuman.second, spellHuman.third)
                }
                Effect.SHIELD -> copyActiveEffects = copyActiveEffects.plus(Pair(Effect.SHIELD, 6))
                Effect.POISON -> copyActiveEffects = copyActiveEffects.plus(Pair(Effect.POISON, 6))
                Effect.RECHARGE -> copyActiveEffects = copyActiveEffects.plus(Pair(Effect.RECHARGE, 5))
            }

            if (spellMonster.first <= 0) {
                //println("  Monster dies!")
                //println()
                if (manaUsed  + spell.value< min)
                    min = manaUsed  + spell.value
                return
            }

            round(spellHuman, spellMonster, copyActiveEffects, manaUsed + spell.value, round + if (!humanMoves) 1 else 0, !humanMoves)
        }
    } else {

        var dmg = newMonster.second - newHuman.third
        if (dmg < 1)
            dmg = 1

        //println("  Boss hits for $dmg.")
        newHuman = Triple(newHuman.first - dmg, newHuman.second, newHuman.third)

        if (newHuman.first <= 0) {
            //println("  Player dies!")
            //println()
            return
        }
        val copyActiveEffects = newActiveEffects.map { pairr -> Pair(pairr.first, pairr.second) }
        round(newHuman, newMonster, copyActiveEffects, manaUsed, round + if (!humanMoves) 1 else 0, !humanMoves)
    }


}

