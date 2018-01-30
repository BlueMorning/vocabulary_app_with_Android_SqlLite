package example.codeclan.com.vocabulary_application.Database;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.StatsModel;


public class DatabaseRunner {


    public static void fuelDatabase(Context context){
        WordsRoomDatabase db = WordsRoomDatabase.getDatabase(context);

        db.statsDao().deleteAllStats();
        db.wordDao().deleteAllWords();
        String[] spelling = {
                "to pull out",
                "shy",
                "oblivion",
                "to pay attention",
                "successful",
                "to get along",
                "crispy",
                "creamy",
                "surrogate",
                "calorific",
                "to deal with",
                "to cope with",
                "to answer back",
                "to win out",
                "gentrification"
        };



        for(int i = 0; i < spelling.length; i++){
            WordEntity word = new WordEntity(  EnumWordType.values()[new Random().nextInt(EnumWordType.values().length)],
                    spelling[i], "");

            Long word_id = db.wordDao().insertWord(word);

            StatsModel stats = new StatsModel(word_id, 0, EnumMasteryLevel.UNKNOWN, 0, 0, 0, 0, 0, 0);
            db.statsDao().insertStats(stats);
        }
    }

}
