package example.codeclan.com.vocabulary_application.Converter;


import android.arch.persistence.room.TypeConverter;

import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;


public class EnumMasteryLevelConverter {
    @TypeConverter
    public static EnumMasteryLevel toEnumMasteryLevel(String masteryLevel) {
        return masteryLevel == null ? null : EnumMasteryLevel.valueOf(masteryLevel);
    }

    @TypeConverter
    public static String toString(EnumMasteryLevel masteryLevel) {
        return masteryLevel == null ? null : masteryLevel.toString();
    }
}
