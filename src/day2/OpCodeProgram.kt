package day2

import java.io.File

object OpCodeProgram {

    @JvmStatic
    fun main(args: Array<String>) {
        go()
    }

    val path = "src/day2/opcode.txt"
    var input: File = File(path)
    var output = -1
    val target = 19690720
    var noun = 0
    var verb = 0

    fun go() {
//        run(fileToArray(input))
//        println(output)


        while (output != target) {
            var intArr = fileToArray(input)
            intArr[1] = noun
            intArr[2] = verb

            run(intArr)
            println("Noun: $noun, Verb: $verb")
            println(output)
            noun++
            verb++
        }

    }


    fun fileToArray(file: File): IntArray {
        val strList = file.readText().split(",").toMutableList()
        val intArr = IntArray(strList.size)
        for ((index, str) in strList.withIndex()) {
            intArr[index] = str.toInt()
        }

        return intArr
    }


    fun run(intArr: IntArray) {
        var y = 0
        var flag = true
        while (y < intArr.size && flag) {
            when (intArr[y]) {
                1 -> y = addOp(intArr, y)
                2 -> y = multOp(intArr, y)
                99 -> flag = false
                else -> print("ERROR")
            }

        }
        output = intArr[0]
    }

    private fun addOp(list: IntArray, index: Int): Int {
        val x = list[list[index + 1]]
        val y = list[list[index + 2]]
        list[list[index + 3]] = (x + y)
        return index + 4
    }

    private fun multOp(list: IntArray, index: Int): Int {
        val x = list[list[index + 1]]
        val y = list[list[index + 2]]
        list[list[index + 3]] = (x * y)
        return index + 4
    }


}