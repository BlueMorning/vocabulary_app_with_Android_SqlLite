package example.codeclan.com.vocabulary_application.Converter;

import android.arch.persistence.room.TypeConverter;

import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

/**
 * Created by horizon on 29/01/2018.
 */

public class EnumTrainingStatusConverter {

    @TypeConverter
    public static EnumTrainingStatus toEnumTrainingStatus(String statusEnum) {
        return statusEnum == null ? null : EnumTrainingStatus.valueOf(statusEnum);
    }

    @TypeConverter
    public static String toString(EnumTrainingStatus statusEnum) {
        return statusEnum == null ? null : statusEnum.name();
    }
}
