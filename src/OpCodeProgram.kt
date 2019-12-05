import java.io.File

class OpCodeProgram {

    val path = "src/files/opcode.txt"
    var input: File = File(path)
    val target = 19690720
    val noun = 0
    val verb = 0

    fun go() {
        run(fileToArray())
    }


    fun fileToArray(): IntArray {
        val strList = input.readText().split(",").toMutableList()
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
        print(intArr[0])
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