package com.example.menuapp
import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

class CovidStatus : AppCompatActivity() {
    var countryCases: String? = null
    var countryCured:kotlin.String? = null
    var countryDeaths:kotlin.String? = null
    var intTotal:kotlin.String? = null
    var localTotal:kotlin.String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_status)
        var settcases = findViewById<View>(R.id.textView13) as TextView
        var ccured = findViewById<View>(R.id.textView2) as TextView
        var cd = findViewById<View>(R.id.textView6) as TextView
        var it = findViewById<View>(R.id.textView9) as TextView
        var lt = findViewById<View>(R.id.textView12) as TextView

        Thread(Runnable {
                val sURL = "https://api.rootnet.in/covid19-in/stats/latest" //DATA SOURCE URL
                // Connect to the URL using java's native library
                var url: URL? = null
                try {
                    url = URL(sURL)
                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                }
                var request: URLConnection? = null
                try {
                    request = url!!.openConnection()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                try {
                    request!!.connect()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                // Convert to a JSON object to print data
                val jp = JsonParser() //from gson
                var root: JsonElement? = null //Convert the input stream to a json element
                try {
                    root = jp.parse(InputStreamReader(request!!.content as InputStream))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val rootobj: JsonObject =
                    root!!.getAsJsonObject() //May be an array, may be an object.
                val data: JsonObject = rootobj.getAsJsonObject("data") //grabbing Json data
                val cdata: JsonObject = data.getAsJsonObject("summary") //grabbing country summary
                Log.d(ContentValues.TAG, "Data: $data")
                Log.d(ContentValues.TAG, "CountryData: $cdata")
                setData(cdata)
            }).start()
         val handler = Handler()
         handler.postDelayed({
             settcases.text = countryCases
             ccured.text = countryCured
             cd.text = countryDeaths
             it.text = intTotal
             lt.text = localTotal
         },1000)

        }

    public fun setData(cdata: JsonObject){

        countryCases = cdata.get("total").toString();
        countryCured = cdata.get("discharged").toString();
        countryDeaths = cdata.get("deaths").toString();
        intTotal = cdata.get("confirmedCasesForeign").toString();
        localTotal = cdata.get("confirmedCasesIndian").toString();
    }

//Setting data into global variables by using JsonObject
}
