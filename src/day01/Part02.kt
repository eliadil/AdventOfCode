
fun main(args: Array<String>) {
    val input = readLine() ?: return;

    var sum = 0;

    var i = 0;
    while( i < input.length)
    {
        sum += if (input[i] == '(') 1 else -1
        if(sum == -1) {
            print(i + 1)
            i = input.length
        }
        i++
    }
}