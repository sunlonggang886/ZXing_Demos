package twd.zx.com.zxing_demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import twd.zx.com.libzxing.activity.CaptureActivity;
import twd.zx.com.libzxing.encoding.EncodingUtils;

public class MainActivity extends Activity {

    private TextView mTextView;
    private EditText mEditText;
    private ImageView mImageView;
    private CheckBox mCheckBox;
    private static final int SUCCESS_RESULT=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView= (TextView) findViewById(R.id.tv_result);
        mEditText= (EditText) findViewById(R.id.et_input);
        mImageView= (ImageView) findViewById(R.id.iv_imageview);
        mCheckBox= (CheckBox) findViewById(R.id.cb_logo);
    }
    //扫描二维码
    public void scan(View v){
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),SUCCESS_RESULT);
    }
    //生成二维码
    public void makeQRCode(View v){
       String content=mEditText.getText().toString();
        if(content.equals("")){
            Toast.makeText(MainActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            Bitmap bitmap= EncodingUtils.createQRCode(content,500,500,mCheckBox.isChecked()? BitmapFactory.decodeResource(getResources(),R.drawable.dhjk):null);
            mImageView.setImageBitmap(bitmap);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK&&requestCode==SUCCESS_RESULT){
            Bundle bundle=data.getExtras();
            String result=bundle.getString("result");
            Log.e("TAG", result);
            mTextView.setText(result);
        }
    }

}
