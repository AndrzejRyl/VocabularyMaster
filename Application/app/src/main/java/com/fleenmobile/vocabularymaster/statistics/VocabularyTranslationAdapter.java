package com.fleenmobile.vocabularymaster.statistics;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 * Adapter containing data about all words that user
 * has learnt
 *
 * @author FleenMobile at 2016-09-24
 */
public class VocabularyTranslationAdapter extends RecyclerView.Adapter<VocabularyTranslationVH>{
    public VocabularyTranslationAdapter(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public VocabularyTranslationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VocabularyTranslationVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
