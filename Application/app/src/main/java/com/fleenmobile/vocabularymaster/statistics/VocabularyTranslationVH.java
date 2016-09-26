package com.fleenmobile.vocabularymaster.statistics;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fleenmobile.vocabularymaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Two textviews. One of them contains vocabulary
 * and the second one the translation of this vocabulary.
 *
 * @author FleenMobile at 2016-09-24
 */
class VocabularyTranslationVH extends RecyclerView.ViewHolder {

    @BindView(R.id.item_vocabulary_translation_word)
    protected TextView word;
    @BindView(R.id.item_vocabulary_translation_translation)
    protected TextView translation;

    VocabularyTranslationVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
