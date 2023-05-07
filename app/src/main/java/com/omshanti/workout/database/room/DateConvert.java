package com.omshanti.workout.database.room;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConvert {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }
}
