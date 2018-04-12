package com.twopai.basedialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：twopai on 2018/4/12.
 * 邮箱：twopai@hotmail.com
 */

public class BaseDialogUtil {
    private static BaseDialogUtil baseDialogUtil;
    private static Object  objLock = new Object();
    private static BaseDialog.Builder builder;
    private static BaseDialog dialog;
    private static TextView tishiText,contentText,cacelText,centerVerBarViewText,confirmText;
    public void destroy(){
        if (baseDialogUtil!= null) {
            baseDialogUtil=null;
        }
        if (builder!= null) {
            builder=null;
        }
        if (tishiText!= null) {
            tishiText=null;
        }
        if (contentText!= null) {
            contentText=null;
        }
        if (cacelText!= null) {
            cacelText=null;
        }
        if (centerVerBarViewText!= null) {
            centerVerBarViewText=null;
        }
        if (confirmText!= null) {
            confirmText=null;
        }
        if (dialog!= null) {
            dialog=null;
        }
    }
    public static BaseDialogUtil getInstance(Context context){
        synchronized (objLock){
            if (baseDialogUtil == null) {
                baseDialogUtil = new BaseDialogUtil();
                builder = new BaseDialog.Builder(context);
            }
        }
        return baseDialogUtil;
    }

    public BaseDialogUtil initBaseDialog(String noteViewStr
            ,String contentViewStr,String cacelViewStr,String confirmViewStr,boolean isShowCacel,boolean isShowCenterVerBar){
        synchronized (objLock){
            if (dialog == null) {
                dialog = builder.setContentView(R.layout.base_dialog)
                        .setCancelable(true)
//                        .formBottom(true)
//                        .fullWidth()
                        .addDefaultAnimation()
                        .create();
                tishiText = dialog.getView(R.id.tishiText);
                contentText = dialog.getView(R.id.contentText);
                cacelText = dialog.getView(R.id.cacelText);
                centerVerBarViewText = dialog.getView(R.id.centerVerBar);
                confirmText = dialog.getView(R.id.confirmText);
            }
        }
        if (dialog != null) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
        if (noteViewStr != null) {
            tishiText.setText(noteViewStr);
        }
        if (contentViewStr != null) {
            contentText.setText(contentViewStr);
        }
        if (cacelViewStr != null) {
            cacelText.setText(cacelViewStr);
        }
        if (confirmViewStr != null) {
            confirmText.setText(confirmViewStr);
        }

        if (isShowCacel) {
            cacelText.setVisibility(View.VISIBLE);
        }else {
            cacelText.setVisibility(View.GONE);
        }
        if (isShowCenterVerBar) {
            centerVerBarViewText.setVisibility(View.VISIBLE);
        }else {
            centerVerBarViewText.setVisibility(View.GONE);
        }
        cacelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                onClickListener.canceleClick(v);
            }
        });

        confirmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                onClickListener.okClick(v);
            }
        });
        return this;
    }
    private static OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        BaseDialogUtil.onClickListener = onClickListener;
    }
    public interface OnClickListener{
        void okClick(View view);
        void canceleClick(View view);
    }
}
