package com.fleenmobile.vocabularymaster.revision.di;

import com.fleenmobile.vocabularymaster.revision.RevisionContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-12
 */
@Module
public class RevisionModule {

    private RevisionContract.View mView;

    public RevisionModule(RevisionContract.View view) {
        mView = view;
    }

    @Provides
    public RevisionContract.View provideRevisionView() {
         return mView;
    }
}
