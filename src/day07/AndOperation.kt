package day07

/**
 * Created by eliadil on 2015-12-07.
 */
class AndOperation(val keyLeft: Any, val keyRight: Any) : Operation() {

    override fun canEvaluate(values: Map<String, Int>): Boolean {
        return (keyLeft is Int || (keyLeft is String && values.containsKey(keyLeft)))
                && (keyRight is Int || (keyRight is String && values.containsKey(keyRight)))
    }


    override fun evaluate(map: Map<String, Int>): Int {
        val left =
                if (keyLeft is String)
                    map[keyLeft]!!
                else
                    keyLeft as Int
        val right =
                if (keyRight is String)
                    map[keyRight]!!
                else
                    keyRight as Int

        return left.and(right)
    }
}