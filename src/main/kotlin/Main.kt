package org.example

import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap



fun ProcessData(dataMap: HashMap<String, MutableList<Double>>) {
//    val sortedMap= dataMap.keys.sorted()

    for ((key, value) in dataMap) {
        print(key)
        print("=")

        print(dataMap.get(key)?.get(0))
        print("/")
        val sum = dataMap.get(key)?.get(2)
        val count = dataMap.get(key)?.get(3)
        val avg = count?.let { sum?.div(it.toInt()) } //check let again
        print(avg)
        print("/")
        println(dataMap.get(key)?.get(1))


    }

}


fun ProcessDataAndSort(dataMap: ConcurrentHashMap<String, MutableList<Double>>) {
    val sortedMap = dataMap.keys.sorted()
    for (key in sortedMap) {
        print(key)
        print("=")

        print(dataMap.get(key)?.get(0))
        print("/")
        var sum = dataMap.get(key)?.get(2)
        var count = dataMap.get(key)?.get(3)
        val avg = sum?.div(count!!) //check
        val avg2digits: Double = String.format("%.2f", avg).toDouble()//check let again
        print(avg2digits)
        print("/")
        println(dataMap.get(key)?.get(1))

    }
}


fun main() {


    val startTime = System.currentTimeMillis()

    val citiesSet = HashSet<String>()

    val filePath = "/Users/tarannums/downloads/measurements.txt"
    var dataMap: ConcurrentHashMap<String, MutableList<Double>>? = null
    val path = Paths.get(filePath)

    Files.lines(Paths.get(filePath)).parallel().forEach { line ->
        val parts = line.split(";")
        val city = parts[0]
        val temp = parts[1].toDouble()

        if (dataMap != null && dataMap.contains(city)) {
            var (min, max, sum, count) = dataMap[city]!!

            if (min > temp) {
                min = temp
            }
            if (max < temp) {
                max = temp
            }
            sum += temp
            count++
            dataMap[city] = mutableListOf(min, max, sum, count)
        } else {
            dataMap?.put(city, mutableListOf(temp, temp, temp, 1.0))
            citiesSet.add(city)

        }
        if (dataMap != null) {
            ProcessDataAndSort(dataMap)
        }


    }
    val endTime = System.currentTimeMillis()
    val elapsedTime = endTime - startTime
    println(elapsedTime)
}





