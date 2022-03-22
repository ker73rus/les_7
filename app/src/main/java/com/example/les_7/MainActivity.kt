package com.example.les_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    val TAG = "CONDITION"
    var condition = 0
    var arrayThread = Gson().fromJson("", Array<ResponseItem>::class.java)
    var arrayExecutor = Gson().fromJson("", Array<ResponseItem>::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        condition = savedInstanceState?.getInt(TAG) ?: 0
        start(condition++)

        setContentView(R.layout.activity_main)
    }

    fun start(c :Int){
        if(c == 0)
        {
            startExecutor()
            startTread()
        }
    }


    fun startTread(){
        val thread = Thread{
            Thread.sleep(5000)
            readJsonThread()
        }
        thread.start()
    }
    fun startExecutor(){
        val executor = Executors.newSingleThreadExecutor()
        executor.submit{
            Thread.sleep(5000)
            readJsonExecutor()
        }
    }

    private fun readJsonExecutor(){
        var json = ""
        try {
            val inputStream:InputStream = assets.open("source.json")
            json = inputStream.bufferedReader().use{it.readText()}
        }
        catch (e : IOException){

        }
        val gson = Gson()
        arrayExecutor = gson.fromJson(json,Array<ResponseItem>::class.java)


        for(i in arrayExecutor){
            Log.d("QQQ2", i.id.toString())
        }
    }



    private fun readJsonThread(){
        var json = ""
        try {
            val inputStream:InputStream = assets.open("source.json")
            json = inputStream.bufferedReader().use{it.readText()}
        }
        catch (e : IOException){

        }
        val gson = Gson()
        arrayThread = gson.fromJson(json,Array<ResponseItem>::class.java)


        for(i in arrayThread){
            Log.d("QQQ1", i.id.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAG, condition)
    }
}