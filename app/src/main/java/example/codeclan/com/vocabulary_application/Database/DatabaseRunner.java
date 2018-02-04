package example.codeclan.com.vocabulary_application.Database;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.MeaningModel;
import example.codeclan.com.vocabulary_application.Model.StatsModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;


public class DatabaseRunner {


    public static void fuelDatabase(WordsRoomDatabase db){

        db.wordTrainingJoinDao().deleteAllWordTrainingJoins();
        db.trainingDao().deleteAllTrainings();
        db.statsDao().deleteAllStats();
        db.meaningDao().deleteAllMeanings();
        db.wordDao().deleteAllWords();


        ArrayList<WordEntity> wordListEntities = new ArrayList<>();
        WordEntity w1 = new WordEntity(EnumWordType.PHRASAL_VERB, "to deal with", "");
        WordEntity w2 = new WordEntity(EnumWordType.PHRASAL_VERB, "to win out", "");
        WordEntity w3 = new WordEntity(EnumWordType.PHRASAL_VERB, "to carry on", "");
        WordEntity w4 = new WordEntity(EnumWordType.PHRASAL_VERB, "to answer back", "");
        wordListEntities.add(w1);
        wordListEntities.add(w2);
        wordListEntities.add(w3);
        wordListEntities.add(w4);

        WordModel wm1 = new WordModel(w1, db);
        MeaningEntity m1 = new MeaningEntity(new Long(0),
                "to take action to do something, especially to solve a problem",
                "The government must now deal with the problem of high unemployment",
                "",
                "");
        wm1.addMeaning(new MeaningModel(m1, wm1));


        MeaningEntity m1_2 = new MeaningEntity(new Long(0),
                "to accept and control a difficult emotional situation so that you can start to live a normal life again despite it",
                "She’s dealing with her father’s death very well.",
                "",
                "");
        wm1.addMeaning(new MeaningModel(m1_2, wm1));


        wm1.saveWord();

        WordModel wm2 = new WordModel(w2, db);
        MeaningEntity m2 = new MeaningEntity(new Long(0),
                "to succeed after great difficulty",
                "It was a struggle, but truth won out in the end.",
                "",
                "");
        wm2.addMeaning(new MeaningModel(m2, wm2));




        wm2.saveWord();

        WordModel wm3 = new WordModel(w3, db);
        MeaningEntity m3 = new MeaningEntity(new Long(0),
                "to continue doing something",
                "He moved to London to carry on his work.",
                "",
                "");
        wm3.addMeaning(new MeaningModel(m3, wm3));
        wm3.saveWord();

        WordModel wm4 = new WordModel(w4, db);
        MeaningEntity m4 = new MeaningEntity(new Long(0),
                "to reply rudely to someone who has more authority than you",
                "Don’t you dare answer me back !",
                "",
                "");
        wm4.addMeaning(new MeaningModel(m4, wm4));
        wm4.saveWord();


        int[] wordCounts = {1,2,3};
        ArrayList<TrainingEntity> trainingsEntitiesList = new ArrayList<>();

        for(int indexTrainingEntity = 0; indexTrainingEntity < (wordListEntities.size()); indexTrainingEntity++){

            TrainingEntity trainingEntity = new TrainingEntity(
                    EnumTrainingStatus.values()[new Random().nextInt(EnumTrainingStatus.values().length-1)+1],
                    indexTrainingEntity+1,wordCounts[new Random().nextInt(wordCounts.length)],new Random().nextInt(11), LocalDate.now().plusDays(new Random().nextInt(3)-1));

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
