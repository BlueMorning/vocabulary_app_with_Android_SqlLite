package example.codeclan.com.vocabulary_application.Model;

import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;

/**
 * Created by horizon on 29/01/2018.
 */

public class MeaningModel extends MeaningEntity {


    public MeaningModel(Long id, Long wordId, String definition, String example, String synonyms, String antonyms) {
        super(id, wordId, definition, example, synonyms, antonyms);
    }

    public MeaningModel(Long wordId, String definition, String example, String synonyms, String antonyms) {
        super(wordId, definition, example, synonyms, antonyms);
    }
}
