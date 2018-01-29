package example.codeclan.com.vocabulary_application.Converter;

import android.arch.persistence.room.TypeConverter;

import java.time.LocalDate;


public class LocalDateTypeConverter {

    @TypeConverter
    public static LocalDate toLocalDate(String value) {
        return value == null ? null : LocalDate.parse(value);
    }

    @TypeConverter
    public static String toString(LocalDate localDate) {
        return localDate == null ? null : localDate.toString();
    }

}
