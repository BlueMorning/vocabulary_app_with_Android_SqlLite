package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;


public class StatsModel {

    private StatsEntity statsEntity;

    public StatsModel(StatsEntity statsEntity){
        this.statsEntity = statsEntity;
    }

    public StatsEntity getStatsEntity() {
        return statsEntity;
    }

    public void setStatsEntity(StatsEntity statsEntity) {
        this.statsEntity = statsEntity;
    }
}
