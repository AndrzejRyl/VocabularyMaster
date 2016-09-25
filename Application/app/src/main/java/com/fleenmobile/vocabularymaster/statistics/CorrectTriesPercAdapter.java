package com.fleenmobile.vocabularymaster.statistics;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 * Adapter displaying data about vocabulary and statistics about
 * how many times this vocabulary has been correctly revised
 *
 * @author FleenMobile at 2016-09-24
 */
public class CorrectTriesPercAdapter extends RecyclerView.Adapter<CorrectTriesPercVH>{
    public CorrectTriesPercAdapter(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public CorrectTriesPercVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CorrectTriesPercVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void addItems(List<Vocabulary> vocabulary) {
        // TODO
    }
}
