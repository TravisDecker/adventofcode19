import java.io.File

class OpCodeProgram {

    val path = "src/files/opcode.txt"
    var input: File = File(path)


    fun go() {
        run(fileToList())

    }


    // THis is not the best lol
    fun fileToList(): MutableList<String> {
        return input.readText().split(",").toMutableList()
    }


    fun run(x: MutableList<String>) {
        var y = 0
        var flag = true
        while (y < x.size && flag) {
            when (x[y]?.toInt()) {
                1 -> y = addOp(x, y)
                2 -> y = multOp(x, y)
                99 -> flag = false
                else -> print("ERROR")
            }

        }
        print(x[0])
    }

    private fun addOp(list: MutableList<String>, index: Int): Int {
        val x = list[list[index + 1].toInt()].toInt()
        val y = list[list[index + 2].toInt()].toInt()
        list[list[index + 3].toInt()] = (x + y).toString()
        return index + 4
    }

    private fun multOp(list: MutableList<String>, index: Int): Int {
        val x = list[list[index + 1].toInt()].toInt()
        val y = list[list[index + 2].toInt()].toInt()
        list[list[index + 3].toInt()] = (x.toInt() * y.toInt()).toString()
        return index + 4
    }


}