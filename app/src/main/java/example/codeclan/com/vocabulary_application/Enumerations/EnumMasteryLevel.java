package example.codeclan.com.vocabulary_application.Enumerations;

/**
 * Created by horizon on 30/01/2018.
 */

public enum EnumMasteryLevel {

    ALL("All", 0),
    UNKNOWN("Unknow", 1),
    LOW("Low", 2),
    MEDIUM("Medium", 3),
    HIGH("High",4);

    private String masteryLabel;
    private Integer masteryScore;


    EnumMasteryLevel(String label, Integer score){
        this.masteryLabel = label;
        this.masteryScore = score;
    }

    public String getLabel(){
        return this.masteryLabel;
    }

    public Integer getScore(){
        return this.masteryScore;
    }
}
