package com.morening.october_userlogin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morening.october_userlogin.R;

/**
 * Created by morening on 2016/10/8.
 */

public class SimpleEditView extends RelativeLayout {

    private final int DEFAULT_TITLE_ICON = 0;
    private final String DEFAULT_TITLE_TEXT = "Simple";
    private final String DEFAULT_EDIT_HINT = "Why don't you have a try?";
    private final boolean DEFAULT_EDIT_IS_PASSWORD = false;

    private int mTitleIcon = DEFAULT_TITLE_ICON;
    private String mTitleText = DEFAULT_TITLE_TEXT;
    private String mEditHint = DEFAULT_EDIT_HINT;
    private boolean bIsPassword = DEFAULT_EDIT_IS_PASSWORD;

    private View mView = null;

    public SimpleEditView(Context context) {
        this(context, null);
    }

    public SimpleEditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        obtainStyledAttrs(attrs);

        mView = LayoutInflater.from(context).inflate(R.layout.simple_editview_layout, this, false);
        initViews();
        addView(mView);
    }

    private void initViews() {
        ImageView titleIconImage = (ImageView) mView.findViewById(R.id.id_simple_editview_title_icon);
        TextView titleTextView = (TextView) mView.findViewById(R.id.id_simple_edittext_title_text);
        if (mTitleIcon > 0){
            titleIconImage.setBackground(getResources().getDrawable(mTitleIcon));
            titleIconImage.setVisibility(VISIBLE);

            titleTextView.setVisibility(GONE);
        } else {
            titleIconImage.setVisibility(GONE);

            titleTextView.setVisibility(VISIBLE);
            titleTextView.setText(mTitleText);
        }

        EditText editText = (EditText) mView.findViewById(R.id.id_simple_edittext_edit);
        editText.setHint(mEditHint);
        if (bIsPassword){
            editText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleEditView);

        mTitleIcon = ta.getResourceId(R.styleable.SimpleEditView_simple_edit_title_icon, mTitleIcon);
        mTitleText = ta.getString(R.styleable.SimpleEditView_simple_edit_title_text);
        if (TextUtils.isEmpty(mTitleText)){
            mTitleText = DEFAULT_TITLE_TEXT;
        }
        mEditHint = ta.getString(R.styleable.SimpleEditView_simple_edit_edit_hint);
        if (TextUtils.isEmpty(mEditHint)){
            mEditHint = DEFAULT_EDIT_HINT;
        }
        bIsPassword = ta.getBoolean(R.styleable.SimpleEditView_simple_edit_is_password, bIsPassword);

        ta.recycle();
    }
}
