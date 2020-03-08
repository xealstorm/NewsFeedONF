package com.onefootball.util

import org.json.JSONArray
import org.json.JSONObject

fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
    for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
}