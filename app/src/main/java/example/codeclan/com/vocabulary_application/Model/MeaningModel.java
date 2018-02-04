package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;

/**
 * Created by horizon on 29/01/2018.
 */

public class MeaningModel {

    private MeaningEntity meaningEntity;
    private WordModel wordModel;

    public MeaningModel(MeaningEntity meaningEntity, WordModel wordModel){
        this.meaningEntity = meaningEntity;
        this.wordModel     = wordModel;
    }

    public MeaningEntity getMeaningEntity() {

        return meaningEntity;
    }

    public void setMeaningEntity(MeaningEntity meaningEntity) {
        this.meaningEntity = meaningEntity;
    }

    public WordModel getWordModel(){
        return this.wordModel;
    }
}
