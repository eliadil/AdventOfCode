/**
 * Created by eliadil on 2015-12-01.
 */

fun main(args: Array<String>) {
    val input = readLine() ?: return;

    val splitString = input.partition { c -> c == '(' };

    val firstLength = splitString.first.length;
    val secondLength = splitString.second.length;

    print("$firstLength - $secondLength = ${firstLength - secondLength}");
}



