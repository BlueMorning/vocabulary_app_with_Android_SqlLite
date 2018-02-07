package example.codeclan.com.vocabulary_application.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumQuestionType;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingWordCount;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.R;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class TrainingModel  {

    public static final int totalTrainingStep = 10;
    public static final int totalPropositionCount = 4;

    private WordsRoomDatabase db;
    private TrainingEntity trainingEntity;
    private ArrayList<WordModel> wordsModelList;
    private ArrayList<QuestionModel> questionsModelList;

    public TrainingModel(TrainingEntity trainingEntity, WordsRoomDatabase db){
        this.trainingEntity         = trainingEntity;
        this.db                     = db;
        this.wordsModelList         = new ArrayList<>();
        this.questionsModelList     = new ArrayList<>();

    }

    public TrainingEntity getTrainingEntity() {
        return trainingEntity;
    }

    public ArrayList<QuestionModel> getQuestionsModelList(){
        return this.questionsModelList;
    }

    public static void createNewTraining(WordsRoomDatabase db, EnumTrainingWordCount enumTrainingWordCount, EnumWordType enumWordType) {

        int trainingNumber = db.trainingDao().getNewTrainingNumber();

        TrainingEntity trainingEntity = new TrainingEntity(EnumTrainingStatus.ONGOING,
                trainingNumber, enumTrainingWordCount.getWordCount(), 1, LocalDate.now());
        trainingEntity.setId(db.trainingDao().insertTraining(trainingEntity));

        int wordAddedCounter = 0;
        for(int wordIndex = 0; wordIndex < enumTrainingWordCount.getWordCount(); wordIndex++){
            WordEntity wordEntity = db.trainingDao().selectNewWordForTraining(enumWordType);
            if(wordEntity != null) {
                db.wordTrainingJoinDao().insertWordTrainingJoin(new WordTrainingJoinEntity(wordEntity.getId(), trainingEntity.getId()));
                wordAddedCounter+=1;
            }
        }

        if(wordAddedCounter != enumTrainingWordCount.getWordCount()) {
            trainingEntity.setTotalWords(wordAddedCounter);
            db.trainingDao().updateTraining(trainingEntity);
        }
    }

    public void setTrainingEntity(TrainingEntity trainingEntity) {
        this.trainingEntity = trainingEntity;
    }

    public int getNumber() {
        return this.getTrainingEntity().getNumber();
    }

    public String getNumberLabel() {
        return String.format("N°%s", this.getTrainingEntity().getNumber());
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

    public LocalDate getBestNextDate() {
        return this.trainingEntity.getNextBestTraining();
    }

    public String getBestNextDateLabel() {
        return this.trainingEntity.getNextBestTraining().format(ISO_LOCAL_DATE);
    }

    public int getImageTiming() {

        if(this.getBestNextDate().isAfter(LocalDate.now())){
            return R.drawable.deadline_later;
        }
        else if (this.getBestNextDate().getDayOfYear() == LocalDate.now().getDayOfYear()
                 && this.getBestNextDate().getYear() == LocalDate.now().getYear()){
            return R.drawable.deadline_now;
        }
        else {
            return R.drawable.deadline_before;
        }
    }

    public int getStepNumber() {
        return this.getTrainingEntity().getStepNumber();
    }

    public String getStepNumberLabel() {
        return String.format("%s / %s", this.getTrainingEntity().getStepNumber(), totalTrainingStep);
    }



    public ArrayList<QuestionModel> initializeQuestions(ArrayList<EnumQuestionType> enumQuestionTypes, Boolean shuffleQuestions){

        this.initializeWordsList();
        this.questionsModelList.clear();

        for(EnumQuestionType enumQuestionType : enumQuestionTypes){

            for(WordModel wordModel : wordsModelList){

                wordModel.initializeMeanings();

                for(MeaningModel wordMeaningModel : wordModel.getMeaningsList()){

                    QuestionModel questionModel = new QuestionModel(enumQuestionType,
                            wordModel,
                            wordMeaningModel,
                            totalPropositionCount,
                            db);

                    questionModel.initializePropositions();
                    questionModel.shuffleAnswers();

                    this.questionsModelList.add(questionModel);
                }
            }
        }

        if(shuffleQuestions){
            shuffleQuestions();
        }

        return this.questionsModelList;
    }

    public void resetAllQuestionScores(){
        for(QuestionModel question : this.questionsModelList){
            question.resetScores();
        }
    }

    public void shuffleQuestions(){
        Collections.shuffle(this.questionsModelList);
    }

    public void updateStats(){

        StatsEntity statsEntity;

        for(WordModel wordModel: this.wordsModelList){
            statsEntity = db.statsDao().getStatsByWordId(wordModel.getWordEntity().getId());
            statsEntity.setLastTrainingTotalAnswers(0);
            statsEntity.setLastTrainingTotalCorrectAnswers(0);
            statsEntity.setLastTrainingTotalIncorrectAnswers(0);
            db.statsDao().updateStats(statsEntity);
        }

        for(QuestionModel questionModel: this.questionsModelList){

            statsEntity = db.statsDao().getStatsByWordId(questionModel.getWordModelToPractice().getWordEntity().getId());

            statsEntity.setTotalAnswers(statsEntity.getTotalAnswers() + questionModel.getCorrectAnswers() + questionModel.getIncorrectAnswers());
            statsEntity.setTotalCorrectAnswers(statsEntity.getTotalIncorrectAnswers()+ questionModel.getCorrectAnswers());
            statsEntity.setTotalIncorrectAnswers(statsEntity.getTotalIncorrectAnswers()+ questionModel.getIncorrectAnswers());

            statsEntity.setLastTrainingTotalAnswers(statsEntity.getLastTrainingTotalAnswers() + questionModel.getCorrectAnswers() + questionModel.getIncorrectAnswers());
            statsEntity.setLastTrainingTotalCorrectAnswers(statsEntity.getLastTrainingTotalCorrectAnswers() + questionModel.getCorrectAnswers());
            statsEntity.setLastTrainingTotalIncorrectAnswers(statsEntity.getLastTrainingTotalIncorrectAnswers() + questionModel.getIncorrectAnswers());

            statsEntity.setTrainingStep(statsEntity.getTrainingStep()+1);

            db.statsDao().updateStats(statsEntity);
        }
    }

    public void delete() {
        db.wordTrainingJoinDao().deleteWordTrainingJoinByTrainingId(this.trainingEntity.getId());
        db.trainingDao().deleteTrainingById(this.trainingEntity.getId());
    }
}
