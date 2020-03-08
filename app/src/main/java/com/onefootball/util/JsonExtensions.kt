package com.onefootball.util

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
    for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
}

fun JSONObject.getStringOrNull(fieldName: String): String? {
    return try {
        this.getString(fieldName)
    } catch (e: JSONException) {
        Log.e(JSONObject::class.java.toString(), e.message ?: "Field not found")
        null
    }
}