package com.fleenmobile.vocabularymaster.adding_words;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.adding_words.domain.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Popup first showing the user button taking him to file manager in order to
 * choose the file and than - through progress bar - it changes to success
 * message or error one
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddFilePopupView extends DialogFragment implements AddFilePopupContract.View {

    private static final int FILE_SELECT_CODE = 0;
    private AddFilePopupContract.Presenter mPresenter;
    private boolean mActive = false;
    private Activity mActivity;

    @BindView(R.id.add_file_popup_header)
    protected TextView headerTV;
    @BindView(R.id.add_file_button)
    protected Button selectButton;
    @BindView(R.id.add_file_error_button)
    protected Button errorButton;
    @BindView(R.id.add_file_success_button)
    protected Button successButton;
    @BindView(R.id.add_file_progress_bar)
    protected ProgressBar progressBar;
    private int addedWordsAmount = 0;

    public static AddFilePopupView newInstance() {
        return new AddFilePopupView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.v_add_file, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (mActive) return;
        super.show(manager, tag);
        mActive = true;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mActive = false;
        super.onDismiss(dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the path
                    Uri uri = data.getData();
                    // Parse file and add vocabulary to DB
                    mPresenter.addVocabulary(uri, mActivity);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void subscribe() {
        mPresenter.subscribe();
    }

    public void unsubscribe() {
        mPresenter.unsubscribe();
    }

    @Override
    public void onSuccess(int amount) {
        addedWordsAmount = amount;
        changeLayout(AddFileLayout.SUCCESS);
    }

    @Override
    public void onProgress() {
        changeLayout(AddFileLayout.PROGRESS);
    }

    @Override
    public void onError() {
        changeLayout(AddFileLayout.ERROR);
    }

    @Override
    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, mActivity.getResources().getString(R.string.select_file)),
                    FILE_SELECT_CODE);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(mActivity, mActivity.getResources().getString(R.string.no_file_explorer),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(AddFilePopupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick(R.id.add_file_button)
    public void onSelectFile(View v) {
        if (!FileUtils.isPermissionGranted(mActivity))
            FileUtils.requestPermission(mActivity);
        else
            mPresenter.showFileChooser();
    }

    @OnClick(R.id.add_file_success_button)
    public void onAnotherOne(View v) {
        changeLayout(AddFileLayout.NORMAL);
    }

    @OnClick(R.id.add_file_error_button)
    public void onErrorExplanation(View v) {
        errorButton.setVisibility(View.GONE);
        headerTV.setText(mActivity.getResources().getString(R.string.add_file_explanation));
    }

    private void changeLayout(AddFileLayout type) {
        hideButtonsAndProgress();
        switch (type) {
            case NORMAL:
                selectButton.setVisibility(View.VISIBLE);
                headerTV.setText(mActivity.getResources().getString(R.string.add_file_header));
                break;
            case PROGRESS:
                progressBar.setVisibility(View.VISIBLE);
                headerTV.setText(mActivity.getResources().getString(R.string.adding_vocabulary));
                break;
            case ERROR:
                errorButton.setVisibility(View.VISIBLE);
                headerTV.setText(mActivity.getResources().getString(R.string.add_file_error));
                break;
            case SUCCESS:
                successButton.setVisibility(View.VISIBLE);
                headerTV.setText(mActivity.getResources().getString(R.string.add_file_success, addedWordsAmount));
                break;
        }
    }

    private void hideButtonsAndProgress() {
        progressBar.setVisibility(View.GONE);
        errorButton.setVisibility(View.GONE);
        successButton.setVisibility(View.GONE);
        selectButton.setVisibility(View.GONE);
    }
}
