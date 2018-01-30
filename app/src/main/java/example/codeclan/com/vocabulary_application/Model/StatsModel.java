package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;


public class StatsModel extends StatsEntity {

    public StatsModel(Long id, Long wordId, int trainingStep, EnumMasteryLevel enumMasteryLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers) {
        super(id, wordId, trainingStep, enumMasteryLevel, totalAnswers, totalCorrectAnswers, totalIncorrectAnswers, lastTrainingTotalAnswers, lastTrainingTotalCorrectAnswers, lastTrainingTotalIncorrectAnswers);
    }

    public StatsModel(Long wordId, int trainingStep, EnumMasteryLevel enumMasteryLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers) {
        super(wordId, trainingStep, enumMasteryLevel, totalAnswers, totalCorrectAnswers, totalIncorrectAnswers, lastTrainingTotalAnswers, lastTrainingTotalCorrectAnswers, lastTrainingTotalIncorrectAnswers);
    }
}
