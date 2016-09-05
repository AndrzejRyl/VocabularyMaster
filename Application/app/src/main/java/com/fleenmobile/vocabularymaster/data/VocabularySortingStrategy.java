package com.fleenmobile.vocabularymaster.data;

/**
 *
 * Representing sorting strategy for vocabulary queries.
 *
 * @author FleenMobile at 2016-09-06
 */
public enum  VocabularySortingStrategy {
    /**
     * Sort vocabulary based on percentage of correct tries (over all tries)
     */
    SORT_BY_CORRECT_TRIES_PERC,

    /**
     * Sort vocabulary based on percentage of correct tries (over all tries) but in descending order
     */
    SORT_BY_CORRECT_TRIES_PERC_DESC
}
