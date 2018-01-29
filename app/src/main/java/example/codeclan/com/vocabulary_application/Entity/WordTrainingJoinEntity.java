package example.codeclan.com.vocabulary_application.Entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

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

    public final int wrd_tra_wrd_id;
    public final int wrd_tra_tra_id;

    public WordTrainingJoinEntity(final int wrd_tra_wrd_id, final int wrd_tra_tra_id){
        this.wrd_tra_wrd_id = wrd_tra_wrd_id;
        this.wrd_tra_tra_id = wrd_tra_tra_id;
    }

}
