package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;

/**
 * Created by horizon on 29/01/2018.
 */

public class MeaningModel {

    private MeaningEntity meaningEntity;

    public MeaningModel(MeaningEntity meaningEntity){
        this.meaningEntity = meaningEntity;
    }

    public MeaningEntity getMeaningEntity() {
        return meaningEntity;
    }

    public void setMeaningEntity(MeaningEntity meaningEntity) {
        this.meaningEntity = meaningEntity;
    }
}
