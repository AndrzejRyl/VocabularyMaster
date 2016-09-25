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
 * Adapter displaying data about vocabulary and statistics about
 * how many times this vocabulary has been correctly revised
 *
 * @author FleenMobile at 2016-09-24
 */
class CorrectTriesPercAdapter extends RecyclerView.Adapter<CorrectTriesPercVH> {

    private Context mContext;
    private List<Vocabulary> mVocabulary;

    CorrectTriesPercAdapter(Context context, List<Vocabulary> vocabulary) {
        mContext = context;
        mVocabulary = vocabulary;
        notifyDataSetChanged();
    }

    @Override
    public CorrectTriesPercVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_correct_tries_word, parent, false);
        return new CorrectTriesPercVH(view);
    }

    @Override
    public void onBindViewHolder(CorrectTriesPercVH holder, int position) {
        Vocabulary vocabulary = mVocabulary.get(position);

        holder.word.setText(vocabulary.getWord());
        holder.perc.setText(String.format("%s/%s", vocabulary.getTotalCorrectTries(), vocabulary.getTotalCorrectTries() + vocabulary.getTotalIncorrectTries()));
    }

    @Override
    public int getItemCount() {
        return mVocabulary.size();
    }

    void addItems(List<Vocabulary> vocabulary) {
        mVocabulary.addAll(vocabulary);
        notifyDataSetChanged();
    }
}
