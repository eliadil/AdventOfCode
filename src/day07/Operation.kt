package day07

/**
 * Created by eliadil on 2015-12-07.
 */
abstract class Operation {

    abstract public fun canEvaluate(values: Map<String, Int>): Boolean
    abstract public fun evaluate(values: Map<String, Int>): Int

}