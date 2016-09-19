package com.fleenmobile.vocabularymaster.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fleenmobile.vocabularymaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author FleenMobile at 2016-09-19
 */
public class NavigationItem extends RelativeLayout {

    LayoutInflater mInflater;

    @BindView(R.id.navigation_item_icon)
    protected ImageView icon;
    @BindView(R.id.navigation_item_name)
    protected TextView name;

    public NavigationItem(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public NavigationItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init() {
        View v = mInflater.inflate(R.layout.navigation_item, this, true);
        ButterKnife.bind(this, v);
    }

    public void setIcon(Drawable drawable) {
        icon.setImageDrawable(drawable);
    }

    public void setName(String value) {
        name.setText(value);
    }
}
