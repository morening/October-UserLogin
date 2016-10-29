package com.morening.october_userlogin.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morening.october_userlogin.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by morening on 2016/10/8.
 */

public class SimpleEditView extends RelativeLayout {

    private final int DEFAULT_STYLE_TITLE_ICON = 0;
    private final String DEFAULT_STYLE_TITLE_TEXT = "Simple";
    private final String DEFAULT_STYLE_EDIT_HINT = "Why don't you have a try?";
    private final boolean DEFAULT_STYLE_EDIT_IS_PASSWORD = false;
    private final boolean DEFAULT_STYLE_EDIT_SHOW_PASSWORD = false;
    private final int DEFAULT_STYLE_IME_OPTION = EditorInfo.IME_ACTION_NONE;

    private int mTitleIconStyle = DEFAULT_STYLE_TITLE_ICON;
    private String mTitleTextStyle = DEFAULT_STYLE_TITLE_TEXT;
    private String mEditHintStyle = DEFAULT_STYLE_EDIT_HINT;
    private boolean bIsPasswordStyle = DEFAULT_STYLE_EDIT_IS_PASSWORD;
    private boolean bShowPwdStyle = DEFAULT_STYLE_EDIT_SHOW_PASSWORD;
    private int mImeOption = DEFAULT_STYLE_IME_OPTION;

    private View mView = null;
    private EditText mEditText = null;
    private ImageView mShowPwdIcon = null;
    private boolean bIsShowPwdEnabled = false;

    private Map<String, Integer> mImeOptionMap = null;

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

        mEditText = (EditText) mView.findViewById(R.id.id_simple_edittext_edit);
        mEditText.setHint(mEditHintStyle);
        mEditText.setImeOptions(mImeOption);
        mShowPwdIcon = (ImageView) mView.findViewById(R.id.id_simple_edittext_show_pwd_icon);
        if (bIsPasswordStyle){
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            if (bShowPwdStyle){
                mShowPwdIcon.setVisibility(VISIBLE);
                mShowPwdIcon.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bIsShowPwdEnabled){
                            bIsShowPwdEnabled = false;
                            mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            mShowPwdIcon.setBackgroundResource(R.drawable.simple_edit_show_pwd_off_icon);
                        } else {
                            bIsShowPwdEnabled = true;
                            mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            mShowPwdIcon.setBackgroundResource(R.drawable.simple_edit_show_pwd_icon);
                        }
                        Selection.setSelection(mEditText.getText(), mEditText.getText().length());
                    }
                });
            } else {
                mShowPwdIcon.setVisibility(GONE);
            }
        } else {
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            mShowPwdIcon.setVisibility(GONE);
        }
    }

    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleEditView);

        mTitleIconStyle = ta.getResourceId(R.styleable.SimpleEditView_title_icon, mTitleIconStyle);
        mTitleTextStyle = ta.getString(R.styleable.SimpleEditView_title_text);
        if (TextUtils.isEmpty(mTitleTextStyle)){
            mTitleTextStyle = DEFAULT_STYLE_TITLE_TEXT;
        }
        mEditHintStyle = ta.getString(R.styleable.SimpleEditView_edit_hint);
        if (TextUtils.isEmpty(mEditHintStyle)){
            mEditHintStyle = DEFAULT_STYLE_EDIT_HINT;
        }
        bIsPasswordStyle = ta.getBoolean(R.styleable.SimpleEditView_enable_password, bIsPasswordStyle);
        bShowPwdStyle = ta.getBoolean(R.styleable.SimpleEditView_show_password, bShowPwdStyle);

        initImeOptionMap();
        String imeOption = ta.getString(R.styleable.SimpleEditView_ime_option);
        mImeOption = getImeOption(imeOption);

        ta.recycle();
    }

    private void initImeOptionMap() {
        mImeOptionMap = new HashMap<>();
        mImeOptionMap.put("actionUnspecified", EditorInfo.IME_ACTION_UNSPECIFIED);
        mImeOptionMap.put("actionDone", EditorInfo.IME_ACTION_DONE);
        mImeOptionMap.put("actionGo", EditorInfo.IME_ACTION_GO);
        mImeOptionMap.put("actionNext", EditorInfo.IME_ACTION_NEXT);
        mImeOptionMap.put("actionNone", EditorInfo.IME_ACTION_NONE);
        mImeOptionMap.put("actionPrevious", EditorInfo.IME_ACTION_PREVIOUS);
        mImeOptionMap.put("actionSearch", EditorInfo.IME_ACTION_SEARCH);
        mImeOptionMap.put("actionSend", EditorInfo.IME_ACTION_SEND);
    }

    private int getImeOption(String imeOption) {

        return mImeOptionMap.get(imeOption);
    }

    public String getText(){
        if (mEditText == null){
            return null;
        }
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        return mEditText.getText().toString();
    }

    public void setOnEditorActionListener(final EditText.OnEditorActionListener listener){
        if (mEditText != null){
            mEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    return listener.onEditorAction(v, actionId, event);
                }
            });
        }
    }

    public void setImeOptions(int imeOptions){
        if (mEditText != null){
            mEditText.setImeOptions(imeOptions);
            mImeOption = imeOptions;
        }
    }

    public void addTextChangedListener(TextWatcher watcher){
        if (mEditText != null){
            mEditText.addTextChangedListener(watcher);
        }
    }

    public void setSelection(int index){
        if (mEditText != null){
            mEditText.setSelection(index);
        }
    }

    public int getCount(){
        if (mEditText == null || mEditText.getText() == null){
            return 0;
        }
        return mEditText.getText().length();
    }
}
