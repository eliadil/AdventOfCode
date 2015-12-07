package day07

/**
 * Created by eliadil on 2015-12-07.
 */
class NotOperation(val key: String): Operation() {

    override fun canEvaluate(values: Map<String, Int>): Boolean {
        return values.containsKey(key)
    }

    override fun evaluate(values: Map<String, Int>): Int {
        return values[key]!!.xor(65535)
    }
}