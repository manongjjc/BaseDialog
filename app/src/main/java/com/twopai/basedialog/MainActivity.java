package com.twopai.basedialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG="MainActivity";
    private BaseDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void popCancelDialog(View view) {
        BaseDialogUtil.getInstance(this).initBaseDialog(null,"酷啊，秀啊，牛逼啊",null,null,true,true)
                .setOnClickListener(new BaseDialogUtil.OnClickListener() {
                    @Override
                    public void okClick(View view) {
                        Log.e(TAG,"popCancelDialog-->okClick");
                    }

                    @Override
                    public void canceleClick(View view) {
                        Log.e(TAG,"popCancelDialog-->canceleClick");
                    }
                });
    }

    public void popNotCancelDialog(View view) {
        BaseDialogUtil.getInstance(this).initBaseDialog(null,"酷啊，秀啊，牛逼啊",null,null,false,false)
                .setOnClickListener(new BaseDialogUtil.OnClickListener() {
                    @Override
                    public void okClick(View view) {
                        Log.e(TAG,"popNotCancelDialog-->okClick");
                    }

                    @Override
                    public void canceleClick(View view) {
                        Log.e(TAG,"popNotCancelDialog-->canceleClick");
                    }
                });
    }
    public void popSelfDialog(View view) {
        //传入layout并进行操作
        dialog = new BaseDialog.Builder(this).setContentView(R.layout.selectheadimg_layout)
                .setCancelable(true)
                .fullWidth()
                .formBottom(true)
                .show();
        TextView albumTextView = dialog.getView(R.id.albumTextView);
        albumTextView.setOnClickListener(this);
        TextView cameraTextView = dialog.getView(R.id.cameraTextView);
        cameraTextView.setOnClickListener(this);
        TextView cancelTextView = dialog.getView(R.id.cancelTextView);
        cancelTextView.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseDialogUtil.getInstance(this).destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.albumTextView:
                if (dialog != null) {
                    dialog.dismiss();
                }
                Toast.makeText(this, "选择相册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cameraTextView:
                if (dialog != null) {
                    dialog.dismiss();
                }
                Toast.makeText(this, "选择相机", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelTextView:
                if (dialog != null) {
                    dialog.dismiss();
                }
                break;
        }
    }
}
