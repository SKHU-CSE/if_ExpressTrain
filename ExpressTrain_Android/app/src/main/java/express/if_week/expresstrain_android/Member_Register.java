package express.if_week.expresstrain_android;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.navermap.NMapPOIflagType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class Member_Register extends AppCompatActivity {

    RadioGroup radioGroup;
    int Type;
    GetJson json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member__register);


        json=new GetJson();
        radioGroup=findViewById(R.id.register_radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.register_player:
                        Type=0;
                        break;
                    case R.id.register_store:
                        Type=1;
                        break;
                }
            }
        });

        Button button=findViewById(R.id.register_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText id=findViewById(R.id.register_id_edit);
                EditText pwd=findViewById(R.id.register_pwd_edit);
                EditText email=findViewById(R.id.register_email_edit);
                EditText name=findViewById(R.id.register_name_edit);

                final String ID_S=id.getText().toString();
                final String pwd_S=pwd.getText().toString();
                final String Email_S=email.getText().toString();
                final String Name_S=name.getText().toString();

                if(ID_S.equals("")||pwd_S.equals("")||Email_S.equals("")||Name_S.equals(""))
                {
                    Toast.makeText(Member_Register.this,"재대로 입력해주세요",Toast.LENGTH_LONG).show();
                }else
                {
                    new Thread() {
                        public void run() {
                            // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                            json.requestWebServer(callback, "newMember.php", "id=" + ID_S,"pw="+pwd_S,"type="+Type,"name="+Email_S,"email="+Name_S);
                        }
                    }.start();

                }

            }
        });

    }
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(okhttp3.Call call, IOException e) {
            Log.d(TAG, "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(okhttp3.Call call, Response response) throws IOException {
            final String body = response.body().string();
            Log.d(TAG, "서버에서 응답한 Body:" + body);

            try {
                JSONObject json=new JSONObject(body);
                if(json.getString("result").equals(true)){
                    finish();
                }else {
                    Member_Register.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Handle UI here
                            Toast.makeText(Member_Register.this, body, Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

}
