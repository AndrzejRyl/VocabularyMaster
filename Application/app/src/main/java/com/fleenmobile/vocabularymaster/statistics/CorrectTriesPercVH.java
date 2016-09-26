package com.fleenmobile.vocabularymaster.statistics;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fleenmobile.vocabularymaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Two text views. One of them contains vocabulary
 * and the other $correct_tries$/$all_tries$
 * where $correct_tries$ is a number of times when the user
 * correctly revised given vocabulary and $all_tries$ is the number
 * of all times when user has seen this vocabulary in Revision part.
 *
 *
 * @author FleenMobile at 2016-09-24
 */
class CorrectTriesPercVH extends RecyclerView.ViewHolder {

    @BindView(R.id.item_correct_tries_word)
    protected TextView word;
    @BindView(R.id.item_correct_tries_perc)
    protected TextView perc;

    CorrectTriesPercVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
