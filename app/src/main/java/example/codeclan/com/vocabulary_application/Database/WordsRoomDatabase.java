package example.codeclan.com.vocabulary_application.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import example.codeclan.com.vocabulary_application.Converter.EnumTrainingStatusConverter;
import example.codeclan.com.vocabulary_application.Converter.EnumWordTypeConverter;
import example.codeclan.com.vocabulary_application.Converter.LocalDateTypeConverter;
import example.codeclan.com.vocabulary_application.Dao.MeaningDao;
import example.codeclan.com.vocabulary_application.Dao.StatsDao;
import example.codeclan.com.vocabulary_application.Dao.TrainingDao;
import example.codeclan.com.vocabulary_application.Dao.WordDao;
import example.codeclan.com.vocabulary_application.Dao.WordTrainingJoinDao;
import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;


@Database(entities = {  WordEntity.class,
                        MeaningEntity.class,
                        StatsEntity.class,
                        TrainingEntity.class,
                        WordTrainingJoinEntity.class}, version = 1)
@TypeConverters({LocalDateTypeConverter.class, EnumWordTypeConverter.class, EnumTrainingStatusConverter.class})
public abstract class WordsRoomDatabase extends RoomDatabase {

    private static WordsRoomDatabase instance;

    public static WordsRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (WordsRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            WordsRoomDatabase.class, "vocab_database").build();
                }
            }
        }
        return instance;
    }

    public abstract WordDao wordDao();

    public abstract MeaningDao meaningDao();

    public abstract TrainingDao trainingDao();

    public abstract StatsDao statsDao();

    public abstract WordTrainingJoinDao wordTrainingJoinDao();

}
