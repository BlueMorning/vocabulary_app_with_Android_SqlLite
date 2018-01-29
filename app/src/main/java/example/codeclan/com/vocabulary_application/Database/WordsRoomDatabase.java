package example.codeclan.com.vocabulary_application.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import example.codeclan.com.vocabulary_application.Converter.LocalDateTypeConverter;
import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;


@Database(entities = {  WordEntity.class,
                        MeaningEntity.class,
                        StatsEntity.class,
                        TrainingEntity.class,
                        WordTrainingJoinEntity.class}, version = 1)
@TypeConverters({LocalDateTypeConverter.class})
public class WordsRoomDatabase extends RoomDatabase {

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





    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
