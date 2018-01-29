package example.codeclan.com.vocabulary_application.Entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "trainings")
public class TrainingEntity {

    // Properties

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="tra_id")
    private Long id;

    @ColumnInfo(name = "tra_status")
    private char status;

    @ColumnInfo(name = "tra_number")
    private int number;

    @ColumnInfo(name = "tra_total_words")
    private int totalWords;

    @ColumnInfo(name = "tra_step_number")
    private int stepNumber;

    @ColumnInfo(name = "tra_next_best_training")
    private LocalDate nextBestTraining;



    public TrainingEntity(Long id, char status, int number, int totalWords, int stepNumber, LocalDate nextBestTraining) {
        this.id                 = id;
        this.status             = status;
        this.number             = number;
        this.totalWords         = totalWords;
        this.stepNumber         = stepNumber;
        this.nextBestTraining   = nextBestTraining;
    }

    @Ignore
    public TrainingEntity(char status, int number, int totalWords, int stepNumber, LocalDate nextBestTraining) {
        this.status             = status;
        this.number             = number;
        this.totalWords         = totalWords;
        this.stepNumber         = stepNumber;
        this.nextBestTraining   = nextBestTraining;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public LocalDate getNextBestTraining() {
        return nextBestTraining;
    }

    public void setNextBestTraining(LocalDate nextBestTraining) {
        this.nextBestTraining = nextBestTraining;
    }
}
