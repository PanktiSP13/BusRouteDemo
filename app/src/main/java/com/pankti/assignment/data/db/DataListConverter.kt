package com.pankti.assignment.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pankti.assignment.domain.RouteTiming

class DataListConverter {
    @TypeConverter fun fromRouteTiming(busTrack: List<RouteTiming>): String {
        val type = object : TypeToken<List<RouteTiming>>() {}.type
        return Gson().toJson(busTrack, type)
    }

    @TypeConverter fun toRouteTiming(busTrackString: String): List<RouteTiming> {
        val type = object : TypeToken<List<RouteTiming>>() {}.type
        return Gson().fromJson(busTrackString, type)
    }
}