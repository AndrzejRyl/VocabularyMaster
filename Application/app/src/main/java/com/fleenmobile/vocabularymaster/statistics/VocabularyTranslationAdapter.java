package com.fleenmobile.vocabularymaster.statistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 * Adapter containing data about all words that user
 * has learnt
 *
 * @author FleenMobile at 2016-09-24
 */
class VocabularyTranslationAdapter extends RecyclerView.Adapter<VocabularyTranslationVH>{

    private Context mContext;
    private List<Vocabulary> mVocabulary;

    VocabularyTranslationAdapter(Context context, List<Vocabulary> vocabulary) {
        mContext = context;
        mVocabulary = vocabulary;
        notifyDataSetChanged();
    }

    @Override
    public VocabularyTranslationVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vocabulary_translation, parent, false);
        return new VocabularyTranslationVH(view);
    }

    @Override
    public void onBindViewHolder(VocabularyTranslationVH holder, int position) {
        Vocabulary vocabulary = mVocabulary.get(position);
        holder.word.setText(vocabulary.getWord());
        // This list contains vocabulary with only one translation (each translation has a separate item)
        holder.translation.setText(vocabulary.getTranslations().get(0).getTranslation());
    }

    @Override
    public int getItemCount() {
        return mVocabulary.size();
    }
}
