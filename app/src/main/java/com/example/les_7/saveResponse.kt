package com.example.les_7

import com.google.gson.Gson

open class saveResponse {
    object array {
        var a = Gson().fromJson("", Array<ResponseItem>::class.java)
    }

}