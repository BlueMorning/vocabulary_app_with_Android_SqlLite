package example.codeclan.com.vocabulary_application.Converter;

import android.arch.persistence.room.TypeConverter;

import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;


public class EnumWordTypeConverter {

    @TypeConverter
    public static EnumWordType toLocalDate(String typeEnum) {
        return typeEnum == null ? null : EnumWordType.valueOf(typeEnum);
    }

    @TypeConverter
    public static String toString(EnumWordType typeEnum) {
        return typeEnum == null ? null : typeEnum.name();
    }
}
