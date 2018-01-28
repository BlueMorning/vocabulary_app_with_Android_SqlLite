package example.codeclan.com.vocabulary_application.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "words")
public class WordEntity {


    // Properties

    @PrimaryKey
    @ColumnInfo(name = "wrd_id")
    public final int id;

    @ColumnInfo(name = "wrd_type")
    private String type;

    @ColumnInfo(name="wrd_spelling")
    private String spelling;

    @ColumnInfo(name = "wrd_metadata")
    private String metadata;

    public WordEntity(int id, String type, String spelling, String metadata) {
        this.id       = id;
        this.type     = type;
        this.spelling = spelling;
        this.metadata = metadata;
    }


    // Setters and Getters

    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
