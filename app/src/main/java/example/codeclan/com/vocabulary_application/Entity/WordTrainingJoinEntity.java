package example.codeclan.com.vocabulary_application.Entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "words_trainings_joins",
        primaryKeys = { "wrd_tra_wrd_id", "wrd_tra_tra_id" },
        foreignKeys = {
                @ForeignKey(    entity          = WordEntity.class,
                                parentColumns   = "wrd_id",
                                childColumns    = "wrd_tra_wrd_id"),
                @ForeignKey(    entity          = TrainingEntity.class,
                                parentColumns   = "tra_id",
                                childColumns    = "wrd_tra_tra_id")
        })
public class WordTrainingJoinEntity {

    @NonNull
    private Long wrd_tra_wrd_id;
    @NonNull
    private Long wrd_tra_tra_id;

    public WordTrainingJoinEntity(Long wrd_tra_wrd_id, Long wrd_tra_tra_id){
        this.wrd_tra_wrd_id = wrd_tra_wrd_id;
        this.wrd_tra_tra_id = wrd_tra_tra_id;
    }

    public java.lang.Long getWrd_tra_wrd_id() {
        return wrd_tra_wrd_id;
    }

    public void setWrd_tra_wrd_id(java.lang.Long wrd_tra_wrd_id) {
        this.wrd_tra_wrd_id = wrd_tra_wrd_id;
    }

    public Long getWrd_tra_tra_id() {
        return wrd_tra_tra_id;
    }

    public void setWrd_tra_tra_id(Long wrd_tra_tra_id) {
        this.wrd_tra_tra_id = wrd_tra_tra_id;
    }
}
