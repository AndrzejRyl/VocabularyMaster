package com.fleenmobile.vocabularymaster.statistics;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.view.RobotoTextView;

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
    protected RobotoTextView word;
    @BindView(R.id.item_vocabulary_translation_translation)
    protected RobotoTextView translation;

    VocabularyTranslationVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
