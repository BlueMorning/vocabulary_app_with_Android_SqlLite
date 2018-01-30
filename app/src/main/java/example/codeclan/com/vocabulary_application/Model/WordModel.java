package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
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

    public WordModel(WordEntity wordEntity){
        this.wordEntity = wordEntity;
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
}
