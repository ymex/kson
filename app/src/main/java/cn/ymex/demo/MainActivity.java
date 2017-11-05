package cn.ymex.demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import cn.ymex.kson.Kson;

public class MainActivity extends AppCompatActivity {
    private TextView tvShow;
    private EditText etInput;
    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String key = etInput.getText().toString().trim();
            String result = Kson.unmarshal(json).find(key).getFirst().string();
            tvShow.setText(result);
        }
    };

    private void logfordemo() {
//        InputStream streamJson = null;
//        try {
//            streamJson = getAssets().open("complex.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Kson kson = Kson.unmarshal(streamJson)

        Kson kson = Kson.unmarshal(json)
                .find("message",
                        "result:code",
                        "man:data->person->age",
                        "day:data->income[0][3][0]->day");
//            String message = kson.getFirst().string();
//            String result = kson.get("result").string();//等价 kson.get("data").string()
//            int day = kson.getLast().Int();
        Log.d("Kson", kson.getAll().toString());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        findViewById(R.id.btn02).setOnClickListener(onClickListener);
        tvShow = (TextView) findViewById(R.id.tvShow);
        etInput = (EditText) findViewById(R.id.etInput);
        json = readTextFromAsets(this, "complex.json");
        logfordemo();
    }


    private String readTextFromAsets(Context context, String fileName) {
        try {
//      Reader reader = new InputStreamReader(context.getClass().getClassLoader().getResourceAsStream("assets/" + fileName));
            Reader reader = new InputStreamReader(context.getAssets().open(fileName));
            BufferedReader in = new BufferedReader(reader);
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
