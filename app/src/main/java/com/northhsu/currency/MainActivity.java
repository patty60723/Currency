package com.northhsu.currency;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Float.parseFloat;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    private EditText ntd;
    private TextView us;  // 出現需要 import 則使用 alt+enter
    private TextView jp;

    private static final float rateToUs = 1/30.9f;
    private static final float rateToJp = 1/0.28f;

    // AppCompatActivity 繼承了 FragmentActivity, 其繼承了 Activity
    // 控制畫面元件(包含顯示、設定內容 與 轉換其它畫面)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 偏好在onCreate時就先取得元件，畫面生成前就先取得元件
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElements(); // ctrl+alt+M 將程式碼抽取成方法
    }

    /**
     * 取得畫面元件
     */
    private void findElements() {
        ntd = findViewById(R.id.input_ntd); // ctrl+alt+F(Field) 提升變數至區域變數
        us = findViewById(R.id.result_us);
        jp = findViewById(R.id.result_jp);
    }

    private float getUsFromNTD(float ntd) {
        return ntd * rateToUs;
    }

    private float getJpFromNTD(float ntd) {
        return ntd * rateToJp;
    }

    public void calc(View view) {
        // Toast.makeText(this, "$" + stringNTD, Toast.LENGTH_LONG).show(); // ctrl+P (可再次顯示參數)
        // 浮動文字
        String stringNTD = ntd.getText().toString();

        if (stringNTD.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Problem")
                    .setMessage("Please enter your NTD amount.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ntd.requestFocus();
                        }
                    })
                    .show();
        } else {
            float ntdToUs = getUsFromNTD(parseFloat(stringNTD));
            float ntdToJp = getJpFromNTD(parseFloat(stringNTD));
            us.setText(valueOf(ntdToUs));
            jp.setText(valueOf(ntdToJp));

            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("USD is " + ntdToUs + "\nJPN is " + ntdToJp)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
}
