package day07

/**
 * Created by eliadil on 2015-12-07.
 */
class LShiftOperation(val key: String, val value: Int): Operation() {

    override fun canEvaluate(values: Map<String, Int>): Boolean {
        return values.containsKey(key)
    }


    override fun evaluate(map: Map<String, Int>): Int {
        return map[key]!!.shl(value)
    }

}