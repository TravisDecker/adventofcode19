package day1

import java.io.File


object Calcfuel {

     val path = "src/files/calcfuelinput.txt"
     var input: File = File(path)
     var total: Int = 0
     var moduleFuel = 0
     var fuelFuel = 0


     fun calcFuel(): Int {
          input.forEachLine { doStuff(it.toInt()) }
          return total
     }

     private fun doStuff(x: Int){
          moduleFuel = doMath(x)
          fuelFuel = calcFuelFuel(moduleFuel)
          total += moduleFuel + fuelFuel
     }

     private fun calcFuelFuel(x : Int) : Int {
          var holder = x
          var output = 0
          while(holder > 0){
               holder = doMath(holder)
               if (holder > 0)
                    output += holder
          }
          return output
     }


     private fun doMath(x : Int) : Int {
          return ((x / 3) - 2)
     }


}
