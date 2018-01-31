package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;


public class StatsModel {

    public static final int totalTrainingStep = 10;

    private StatsEntity statsEntity;

    public StatsModel(StatsEntity statsEntity){
        this.statsEntity = statsEntity;
    }

    public StatsModel(Long wordId){
        this.statsEntity = new StatsEntity(wordId, 0, EnumMasteryLevel.NEW, 0 ,0, 0, 0, 0, 0);
    }

    public StatsEntity getStatsEntity() {
        return statsEntity;
    }

    public void setStatsEntity(StatsEntity statsEntity) {
        this.statsEntity = statsEntity;
    }

    public String getTrainingStepLabel() {
        return String.format("%s / %s", statsEntity.getTrainingStep(), totalTrainingStep);
    }

}
