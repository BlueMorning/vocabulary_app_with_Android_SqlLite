package example.codeclan.com.vocabulary_application.Model;

import java.time.LocalDate;

import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;

/**
 * Created by horizon on 29/01/2018.
 */

public class TrainingModel extends TrainingEntity {

    public TrainingModel(Long id, EnumTrainingStatus status, int number, int totalWords, int stepNumber, LocalDate nextBestTraining) {
        super(id, status, number, totalWords, stepNumber, nextBestTraining);
    }

    public TrainingModel(EnumTrainingStatus status, int number, int totalWords, int stepNumber, LocalDate nextBestTraining) {
        super(status, number, totalWords, stepNumber, nextBestTraining);
    }
}
