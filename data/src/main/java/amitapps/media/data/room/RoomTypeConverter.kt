package amitapps.media.data.room

import amitapps.media.domain.model.Owner
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomTypeConverter {

    @TypeConverters
    fun ownerToString(owner: Owner): String {
        return Gson().toJson(owner)
    }

    @TypeConverters
    fun stringToOwner(str: String) : Owner {
        return Gson().fromJson(str, Owner::class.java)
    }
}

class ListOfStringToStringTypeConverter {

    @TypeConverters
    fun listOfStringToString(str: List<String>): String {
        return Gson().toJson(str)
    }

    @TypeConverters
    fun strToListOfString(str: String) : List<String> {
        return Gson().fromJson(str, object: TypeToken<List<String>>() {}.type)
    }
}