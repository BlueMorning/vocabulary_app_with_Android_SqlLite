package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.R;


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

    public int getTrainingStep() {
        return statsEntity.getTrainingStep();
    }

    public String getTrainingStepLabel() {
        return String.format("%s / %s", statsEntity.getTrainingStep(), totalTrainingStep);
    }

    public int getMasteryLevelDrawableId(){

        if(this.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.HIGH){
            return R.drawable.high_mastery;
        }
        else if (this.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.MEDIUM){
            return R.drawable.medium_mastery;
        }
        else if (this.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.LOW) {
            return R.drawable.low_mastery;
        }
        else if (this.getStatsEntity().getMasteryLevel() == EnumMasteryLevel.NEW) {
            return R.drawable.new_mastery;
        }
        else {
            return R.drawable.new_mastery;
        }
    }

    public int getTotalAnswers(){
        return this.getStatsEntity().getTotalAnswers();
    }

    public int getTotalCorrectAnswers(){
        return this.getStatsEntity().getTotalCorrectAnswers();
    }

    public double getTotalCorrectAnswersPercentage(){

        if(getTotalAnswers() > 0) {
            return new Double(getTotalCorrectAnswers()) / getTotalAnswers();
        }
        else{
            return 0;
        }
    }

    public int getTotalIncorrectAnswers(){
        return this.getStatsEntity().getTotalIncorrectAnswers();
    }

    public double getTotalIncorrectAnswersPercentage(){

        if(getTotalAnswers() > 0) {
            return new Double(getTotalIncorrectAnswers()) / getTotalAnswers();
        }
        else{
            return 0;
        }
    }


    public int getLastTrainingTotalAnswers(){
        return this.getStatsEntity().getLastTrainingTotalAnswers();
    }

    public int getLastTrainingTotalCorrectAnswers(){
        return this.getStatsEntity().getLastTrainingTotalCorrectAnswers();
    }

    public double getLastTrainingTotalCorrectAnswersPercentage(){

        if(getTotalAnswers() > 0) {
            return new Double(getLastTrainingTotalCorrectAnswers()) / getLastTrainingTotalAnswers();
        }
        else{
            return 0;
        }
    }

    public int getLastTrainingTotalIncorrectAnswers(){
        return this.getStatsEntity().getLastTrainingTotalIncorrectAnswers();
    }

    public double getLastTrainingTotalIncorrectAnswersPercentage(){

        if(getTotalAnswers() > 0) {
            return new Double(getLastTrainingTotalIncorrectAnswers()) / getLastTrainingTotalAnswers();
        }
        else{
            return 0;
        }
    }

    public String getLastTrainingCorrectAnswersLabel(){
        return String.format("%s / %s", this.getLastTrainingTotalCorrectAnswers(), this.getLastTrainingTotalAnswers());
    }

    public String getLastTrainingIncorrectAnswersLabel(){
        return String.format("%s / %s", this.getLastTrainingTotalIncorrectAnswers(), this.getLastTrainingTotalAnswers());
    }

    public String getTotalCorrectAnswersLabel(){
        return String.format("%s / %s", this.getTotalCorrectAnswers(), this.getTotalAnswers());
    }

    public String getTotalIncorrectAnswersLabel(){
        return String.format("%s / %s", this.getTotalIncorrectAnswers(), this.getTotalAnswers());
    }

    public String getLastTrainingTotalCorrectAnswersPercentageLabel(){
        return String.format("%.0f%%", this.getLastTrainingTotalCorrectAnswersPercentage()*100);
    }

    public String getLastTrainingTotalIncorrectAnswersPercentageLabel(){
        return String.format("%.0f%%", this.getLastTrainingTotalIncorrectAnswersPercentage()*100);
    }

    public String getTotalCorrectAnswersPercentageLabel(){
        return String.format("%.0f%%", this.getTotalCorrectAnswersPercentage()*100);
    }

    public String getTotalIncorrectAnswersPercentageLabel(){
        return String.format("%.0f%%", this.getTotalIncorrectAnswersPercentage()*100);
    }

}
