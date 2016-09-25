package com.fleenmobile.vocabularymaster.statistics;

import android.support.v7.widget.RecyclerView;
import android.view.View;

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
public class CorrectTriesPercVH extends RecyclerView.ViewHolder{
    public CorrectTriesPercVH(View itemView) {
        super(itemView);
    }
}
