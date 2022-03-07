package com.example.earthquakemonitor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainRepository {

     suspend fun fetchEarthquakes():MutableList<Earthquake>{

        return withContext(Dispatchers.IO){
            val eqJsonResponse = service.getLastHourEarthquakes()
            //   Log.d("Manzana",eqJsonResponse)
            val eqList =parseEqResult2(eqJsonResponse)

            eqList
        }

    }

    private fun parseEqResult2(eqJsonResponse:EqJsonResponse): MutableList<Earthquake> {

        val eqList = mutableListOf<Earthquake>()
        val featureList=eqJsonResponse.features

        for (feature in featureList){
            val properties= feature.properties
            val id = feature.id
            val magnitude = properties.mag
            val place = properties.place
            val time = properties.time
            val geometry= feature.geometry

            val longitude = geometry.longitude
            val latitude = geometry.latitude


            eqList.add(Earthquake(id, place, magnitude, time, longitude, latitude))



        }

        return eqList
    }

    private fun parseEqResult(eqListString: String):MutableList<Earthquake> {
        val eqJsonObject = JSONObject(eqListString)
        val featureJsonArray= eqJsonObject.getJSONArray("features")

        val eqList = mutableListOf<Earthquake>()

        for (i in 0 until featureJsonArray.length()){

            val featuresJsonObject = featureJsonArray[i] as JSONObject
            val id =featuresJsonObject.getString("id")

            val propertiesJsonObject = featuresJsonObject.getJSONObject("properties")
            val magnitude:Double=propertiesJsonObject.getDouble("mag")
            val place:String=propertiesJsonObject.getString("place")
            val time:Long=propertiesJsonObject.getLong("time")


            val geometryJsonObject= featuresJsonObject.getJSONObject("geometry")
            val coordinatesJsonArray=geometryJsonObject.getJSONArray("coordinates")
            val latitude= coordinatesJsonArray.getDouble(1)
            val longitude = coordinatesJsonArray.getDouble(0)

            val earthquake= Earthquake(id,place, magnitude, time, longitude, latitude)
            eqList.add(earthquake)


        }
        return eqList

    }
}