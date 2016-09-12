package com.fleenmobile.vocabularymaster.utils;

/**
 * @author FleenMobile at 2016-09-12
 */
public enum GoogleAnalyticsEvent {
    TEST("test", "test", "test");

    private String category, action, label;
    private long value;

    GoogleAnalyticsEvent(String category, String action, String label) {
        this.category = category;
        this.action = action;
        this.label = label;
    }

    GoogleAnalyticsEvent(String category, String action, long value) {
        this.category = category;
        this.action = action;
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return category;
    }

    public String getAction() {
        return action;
    }

    public String getLabel() {
        return label;
    }

    public long getValue() {
        return value;
    }
}
