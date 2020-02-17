package com.example.rulebook

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson

// To call this use context.jsonToClass<MyObject>(R.raw.my_object)
inline fun <reified T> Context.jsonToClass(@RawRes resourceId: Int): T =
    Gson().fromJson(resources.openRawResource(resourceId).bufferedReader().use { it.readText() }, T::class.java)
