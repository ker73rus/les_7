package com.example.les_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.les_7.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val TAG = "CONDITION"
    var condition = 0
    var arrayThread = Gson().fromJson("", Array<ResponseItem>::class.java)
    var arrayExecutor = Gson().fromJson("", Array<ResponseItem>::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        condition = savedInstanceState?.getInt(TAG) ?: 0
        setContentView(binding.root)
        start(condition++)
    }

    fun start(c: Int) {

        val tempThread = Thread {
            Thread.sleep(5000)
            if(saveResponse.array.a != null)
            {
                arrayThread = saveResponse.array.a
            }
            else if (c == 0) {
                startExecutor()
                startTread()
            }

            Thread.sleep(1000)
            runOnUiThread() {
                binding.progressbar.visibility = View.GONE
                val layoutManager = LinearLayoutManager(this)
                binding.recyclerView.layoutManager = layoutManager
                val adapter = Adapter(arrayThread)
                binding.recyclerView.adapter = adapter
            }
        }
        tempThread.start()

    }


    fun startTread() {
        val thread = Thread {

            readJsonThread()
        }
        thread.start()
    }

    fun startExecutor() {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit {

            readJsonExecutor()
        }
    }

    private fun readJsonExecutor() {
        var json = ""
        try {
            val inputStream: InputStream = assets.open("source.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {

        }
        val gson = Gson()
        arrayExecutor = gson.fromJson(json, Array<ResponseItem>::class.java)
    }


    private fun readJsonThread() {
        var json = ""
        try {
            val inputStream: InputStream = assets.open("source.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {

        }
        val gson = Gson()
        arrayThread = gson.fromJson(json, Array<ResponseItem>::class.java)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAG, condition)
        if(arrayThread!=null)
            saveResponse.array.a = arrayThread
    }
}