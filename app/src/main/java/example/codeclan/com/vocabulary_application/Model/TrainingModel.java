package example.codeclan.com.vocabulary_application.Model;

import java.time.LocalDate;

import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;

/**
 * Created by horizon on 29/01/2018.
 */

public class TrainingModel  {

    private TrainingEntity trainingEntity;

    public TrainingModel(TrainingModel trainingEntity){
        trainingEntity = trainingEntity;
    }

    public TrainingEntity getTrainingEntity() {
        return trainingEntity;
    }

    public void setTrainingEntity(TrainingEntity trainingEntity) {
        this.trainingEntity = trainingEntity;
    }
}
