package example.codeclan.com.vocabulary_application.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class TrainingModel  {

    private WordsRoomDatabase db;
    private TrainingEntity trainingEntity;
    private ArrayList<WordModel> wordsModelList;

    public TrainingModel(TrainingEntity trainingEntity, WordsRoomDatabase db){
        this.trainingEntity = trainingEntity;
        this.db             = db;
    }

    public TrainingEntity getTrainingEntity() {
        return trainingEntity;
    }

    public void setTrainingEntity(TrainingEntity trainingEntity) {
        this.trainingEntity = trainingEntity;
    }

    public int getNumber() {
        return this.getTrainingEntity().getNumber();
    }

    public ArrayList<WordModel> getwordsModelList(){
        return this.wordsModelList;
    }

    public void initializeWordsList() {
        this.wordsModelList = new ArrayList<>(
                    db.trainingDao().getWordsByTrainingId(this.getTrainingEntity().getId())
                        .stream().map(wordEntity -> {
                                        return new WordModel(wordEntity, db);
                }).collect(Collectors.toList()));
    }

    public int getTotalWords() {
        return this.trainingEntity.getTotalWords();
    }

    public String getBestNextDateLabel() {
        return this.trainingEntity.getNextBestTraining().format(ISO_LOCAL_DATE);
    }
}
