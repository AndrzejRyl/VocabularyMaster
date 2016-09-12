package com.fleenmobile.vocabularymaster.revision.di;

import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.revision.RevisionActivity;

import dagger.Component;

/**
 * @author FleenMobile at 2016-09-12
 */
@RevisionScope
@Component(dependencies = DataComponent.class, modules = {RevisionModule.class})
public interface RevisionComponent {

    void inject(RevisionActivity revisionActivity);
}
