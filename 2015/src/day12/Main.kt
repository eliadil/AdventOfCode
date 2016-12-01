package day12

import com.google.gson.*
import com.google.gson.stream.JsonReader
import java.io.FileReader
import java.util.*

/**
 * Created by eliadil on 2015-12-15.
 */

fun main(args: Array<String>) {

    val sth = HashMap<String, Any>()

    val gson = Gson()
    val parser = JsonParser()
    val structure = parser.parse(JsonReader(FileReader("resources/day12")))

    println(sumStructure(structure))
}

fun sumStructure(structure: JsonElement): Double
{
    if(structure is JsonPrimitive)
    {
        if(structure.isNumber)
            return structure.asDouble
    }
    else if(structure is JsonArray)
    {
        return structure.asJsonArray.sumByDouble { x->sumStructure(x) }
    }
    else if(structure is JsonObject)
    {
        val jsonOb = structure.asJsonObject
        var found = false
        for(entry in jsonOb.entrySet())
        {
            if(entry.value is JsonPrimitive && entry.value.asJsonPrimitive.isString && entry.value.asString.equals("red"))
                found = true
        }
        if(found)
            return 0.0
        else
        {
            var sum = 0.0
            for(entry in jsonOb.entrySet()) {
                sum += sumStructure(entry.value)
            }
            return sum
        }
    }
    return 0.0
}