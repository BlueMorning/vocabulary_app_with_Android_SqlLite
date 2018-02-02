package example.codeclan.com.vocabulary_application.Database;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.StatsModel;


public class DatabaseRunner {


    public static void fuelDatabase(WordsRoomDatabase db){

        db.wordTrainingJoinDao().deleteAllWordTrainingJoins();
        db.trainingDao().deleteAllTrainings();
        db.statsDao().deleteAllStats();
        db.meaningDao().deleteAllMeanings();
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
                "gentrification",
                "to hold fast to",
                "in plain sight",
                "to attract overseas"
        };


        ArrayList<WordEntity> wordListEntities = new ArrayList<>();
        for(int i = 0; i < spelling.length; i++){
            WordEntity word = new WordEntity(  EnumWordType.values()[new Random().nextInt(EnumWordType.values().length-1)+1],
                    spelling[i], "");

            word.setId(db.wordDao().insertWord(word));

            StatsEntity stats = new StatsEntity(word.getId(), new Random().nextInt(11), EnumMasteryLevel.values()[new Random().nextInt(EnumMasteryLevel.values().length-1)+1], 20, 15, 5, 5, 4, 1);
            db.statsDao().insertStats(stats);

            wordListEntities.add(word);
        }



        ArrayList<TrainingEntity> trainingsEntitiesList = new ArrayList<>();

        for(int indexTrainingEntity = 0; indexTrainingEntity < (wordListEntities.size()-5); indexTrainingEntity++){

            TrainingEntity trainingEntity = new TrainingEntity(
                    EnumTrainingStatus.values()[new Random().nextInt(EnumTrainingStatus.values().length-1)+1],
                    indexTrainingEntity+1,new Random().nextInt(10),new Random().nextInt(11), LocalDate.now().plusDays(new Random().nextInt(3)-1));

            trainingEntity.setId(db.trainingDao().insertTraining(trainingEntity));
            trainingsEntitiesList.add(trainingEntity);

            ArrayList<WordEntity> wordListEntitiesTemp = new ArrayList<>(wordListEntities);
            for(int wordIndex = 0; wordIndex < trainingEntity.getTotalWords(); wordIndex++){

                int indexWordAdded = new Random().nextInt(wordListEntitiesTemp.size());
                WordEntity wordEntityTemp = wordListEntitiesTemp.get(indexWordAdded);

                db.wordTrainingJoinDao().insertWordTrainingJoin(
                        new WordTrainingJoinEntity(
                                wordEntityTemp.getId()
                                , trainingEntity.getId())
                );

                wordListEntitiesTemp.remove(wordEntityTemp);
            }


        }
    }

}
