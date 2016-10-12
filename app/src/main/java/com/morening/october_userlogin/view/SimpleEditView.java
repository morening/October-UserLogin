package com.morening.october_userlogin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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

    private final int DEFAULT_STYLE_TITLE_ICON = 0;
    private final String DEFAULT_STYLE_TITLE_TEXT = "Simple";
    private final String DEFAULT_STYLE_EDIT_HINT = "Why don't you have a try?";
    private final boolean DEFAULT_STYLE_EDIT_IS_PASSWORD = false;
    private final boolean DEFAULT_STYLE_EDIT_SHOW_PASSWORD = false;

    private int mTitleIconStyle = DEFAULT_STYLE_TITLE_ICON;
    private String mTitleTextStyle = DEFAULT_STYLE_TITLE_TEXT;
    private String mEditHintStyle = DEFAULT_STYLE_EDIT_HINT;
    private boolean bIsPasswordStyle = DEFAULT_STYLE_EDIT_IS_PASSWORD;
    private boolean bShowPwdStyle = DEFAULT_STYLE_EDIT_SHOW_PASSWORD;

    private View mView = null;
    private EditText mEidtText = null;
    private ImageView mShowPwdIcon = null;
    private boolean bIsShowPwdEnabled = false;

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
        if (mTitleIconStyle > 0){
            titleIconImage.setBackground(getResources().getDrawable(mTitleIconStyle));
            titleIconImage.setVisibility(VISIBLE);

            titleTextView.setVisibility(GONE);
        } else {
            titleIconImage.setVisibility(GONE);

            titleTextView.setVisibility(VISIBLE);
            titleTextView.setText(mTitleTextStyle);
        }

        mEidtText = (EditText) mView.findViewById(R.id.id_simple_edittext_edit);
        mEidtText.setHint(mEditHintStyle);
        mShowPwdIcon = (ImageView) mView.findViewById(R.id.id_simple_edittext_show_pwd_icon);
        if (bIsPasswordStyle){
            mEidtText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            if (bShowPwdStyle){
                mShowPwdIcon.setVisibility(VISIBLE);
                mShowPwdIcon.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bIsShowPwdEnabled){
                            bIsShowPwdEnabled = false;
                            mEidtText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                            mShowPwdIcon.getBackground().setTint(Color.BLUE);
                        } else {
                            bIsShowPwdEnabled = true;
                            mEidtText.setInputType(InputType.TYPE_CLASS_NUMBER);
                            mShowPwdIcon.getBackground().setTint(Color.GREEN);
                        }
                    }
                });
            } else {
                mShowPwdIcon.setVisibility(GONE);
            }
        } else {
            mEidtText.setInputType(InputType.TYPE_CLASS_TEXT);
            mShowPwdIcon.setVisibility(GONE);
        }
    }

    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleEditView);

        mTitleIconStyle = ta.getResourceId(R.styleable.SimpleEditView_simple_edit_title_icon, mTitleIconStyle);
        mTitleTextStyle = ta.getString(R.styleable.SimpleEditView_simple_edit_title_text);
        if (TextUtils.isEmpty(mTitleTextStyle)){
            mTitleTextStyle = DEFAULT_STYLE_TITLE_TEXT;
        }
        mEditHintStyle = ta.getString(R.styleable.SimpleEditView_simple_edit_edit_hint);
        if (TextUtils.isEmpty(mEditHintStyle)){
            mEditHintStyle = DEFAULT_STYLE_EDIT_HINT;
        }
        bIsPasswordStyle = ta.getBoolean(R.styleable.SimpleEditView_simple_edit_is_password, bIsPasswordStyle);
        bShowPwdStyle = ta.getBoolean(R.styleable.SimpleEditView_simple_edit_show_pwd, bShowPwdStyle);

        ta.recycle();
    }

    public String getText(){
        if (mEidtText == null){
            return null;
        }
        return mEidtText.getText().toString();
    }
}
