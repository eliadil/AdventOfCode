package day07

/**
 * Created by eliadil on 2015-12-07.
 */
class AssignmentOperation(val value: Any): Operation() {

    override fun canEvaluate(values: Map<String, Int>): Boolean {
        return value is Int || values.containsKey(value as String)
    }


    override fun evaluate(values: Map<String, Int>): Int {
        if(value is Int)
            return value
        else
            return values[value as String]!!
    }


}