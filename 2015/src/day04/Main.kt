package day04

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by eliadil on 2015-12-04.
 */
fun main(args: Array<String>) {

    val key = "ckczppom";
    val messageDigest = MessageDigest.getInstance("MD5");

    for(i in 1..10000000)
    {
        val bytes = "$key$i".toByteArray();

        val md5hash = messageDigest.digest(bytes)
        val stringHash = BigInteger(1, md5hash).toString(16);
        if (stringHash.length == 26) {
            println(i)
            println(stringHash)
        }
    }
}