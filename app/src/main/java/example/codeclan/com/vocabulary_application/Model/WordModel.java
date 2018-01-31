package example.codeclan.com.vocabulary_application.Model;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.R;

/**
 * Created by horizon on 29/01/2018.
 */

public class WordModel {

    private WordEntity  wordEntity;
    private StatsModel  statsModel;
    private ArrayList<MeaningModel> meaningsList;

    public WordModel(WordEntity wordEntity){
        this.wordEntity = wordEntity;
    }

    public WordModel(String wordSpelling, EnumWordType enumWordType){
        this.wordEntity = new WordEntity(enumWordType, wordSpelling, "");
    }

    public WordEntity getWordEntity(){
        return this.wordEntity;
    }

    public StatsModel getStatsModel(){
        return this.statsModel;
    }

    public int getMasteryLevelDrawableId(){

        if(this.statsModel.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.HIGH){
            return R.drawable.high_mastery;
        }
        else if (this.statsModel.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.MEDIUM){
            return R.drawable.medium_mastery;
        }
        else if (this.statsModel.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.LOW) {
            return R.drawable.low_mastery;
        }
        else if (this.statsModel.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.NEW) {
            return R.drawable.new_mastery;
        }
        else {
            return R.drawable.new_mastery;
        }
    }

    public void setStatsModel(StatsModel statsModel) {
        this.statsModel = statsModel;
    }

    public void addMeaning(String meaning, String example, String synonyms, String antonyms){
        MeaningEntity meaningEntity = new MeaningEntity(this.wordEntity.getId(), meaning, example, synonyms, antonyms);
        MeaningModel meaningModel   = new MeaningModel(meaningEntity);
        meaningsList.add(meaningModel);
    }

    public void saveWord(WordsRoomDatabase db){

        if(this.wordEntity.getId() == 0){
            this.wordEntity.setId(db.wordDao().insertWord(this.wordEntity));
        }
        else{
            db.wordDao().updateWord(this.wordEntity);
        }

        for(MeaningModel meaningModel : this.meaningsList){

            if(meaningModel.getMeaningEntity().getId() == 0){
                meaningModel.getMeaningEntity().setWordId(this.wordEntity.getId());
                db.meaningDao().insertMeaning(meaningModel.getMeaningEntity());
            }
            else{
                db.meaningDao().updateMeaning(meaningModel.getMeaningEntity());
            }
        }
    }



}
