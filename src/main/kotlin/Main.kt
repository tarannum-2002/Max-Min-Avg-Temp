package org.example

import java.io.*


fun ProcessData(dataMap:HashMap<String, MutableList<Double>> ){
//    val sortedMap= dataMap.keys.sorted()

    for ((key,value) in dataMap) {
        print(key)
        print("=")

        print(dataMap.get(key)?.get(0) ?: null)
        print("/")
        var sum = dataMap.get(key)?.get(2) ?: null
        var count= dataMap.get(key)?.get(3) ?: null
        var avg = count?.let { sum?.div(it.toInt()) } //check let again
        print(avg)
        print("/")
        println(dataMap.get(key)?.get(1) ?: null)


    }

}



fun ProcessDataAndSort(dataMap:HashMap<String, MutableList<Double>> ){
    val sortedMap= dataMap.keys.sorted()
    for(key in sortedMap){
        print(key)
        print("=")

        print(dataMap.get(key)?.get(0))
        print("/")
        var sum = dataMap.get(key)?.get(2)
        var count= dataMap.get(key)?.get(3)
        val avg = sum?.div(count!!) //check
        val avg2digits:Double = String.format("%.2f", avg).toDouble()//check let again
        print(avg2digits)
        print("/")
        println(dataMap.get(key)?.get(1))

    }
}




fun main() {


    val startTime = System.currentTimeMillis()

    val citiesSet = HashSet<String>()

    val filePath = "/Users/tarannums/downloads/measurements.txt"
    var dataMap : HashMap<String, MutableList<Double>> = HashMap()


    File(filePath).bufferedReader().use { reader ->
        var line: String? = reader.readLine()
        while (line != null) {
            val parts = line.split(";")
            val city = parts[0]
            val temp = parts[1].toDouble()

            if (dataMap.contains(city)) {
                var min = dataMap[city]!!.get(0)
                var max = dataMap[city]!!.get(1)
                var sum = dataMap[city]!!.get(2)
                var count = dataMap[city]!!.get(3)

                if(min > temp){
                    min= temp
                }
                if(max< temp){
                    max = temp
                }
                sum = sum + temp
                count = count+1

                dataMap.put(city, mutableListOf(min,max,sum,count))



//                val fileName = "${city}.txt"
//                val outputFile = File(fileName)
//                PrintWriter(outputFile).use { writer ->
//
//                    try {
//                        val doubleValue = parts[1].toDouble()
//                        writer.println(doubleValue)
//                    } catch (e: Exception) {
//
//                        e.printStackTrace()
//                    }
//                }

            }
            else{

                dataMap.put(city, mutableListOf(temp,temp,temp,1.0))
                citiesSet.add(city)
                println(city)
            }

            line = reader.readLine()
        }
        reader.close()
        ProcessDataAndSort(dataMap)

    }

    val endTime = System.currentTimeMillis()
    val elapsedTime = endTime - startTime
    println(elapsedTime)




}





