package express.if_week.expresstrain_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity {


    GetJson json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText etID = findViewById(R.id.login_id_edit);
        final EditText etPW = findViewById(R.id.login_pw_edit);

        Button btLogin = findViewById(R.id.btLogin);
        Button btRegister = findViewById(R.id.btRegister);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(etID.getText().toString().equals("")||etPW.getText().toString().equals("")){
                    Toast.makeText(Login.this,"재대로 입력해주세요",Toast.LENGTH_LONG).show();
                }else {
                    new Thread() {
                        public void run() {
                            // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                            json.requestWebServer(callback, "logun.php", "id=" + etID.getText().toString(), "pw=" + etPW.getText().toString());
                        }
                    }.start();
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Member_Register.class);
                startActivity(intent);
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

            if(body.equals("로그인 성공"))
            {
                finish();
            }else {
                Login.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Handle UI here
                        Toast.makeText(Login.this, body, Toast.LENGTH_LONG).show();
                    }
                });
            }

        }
    };
}
