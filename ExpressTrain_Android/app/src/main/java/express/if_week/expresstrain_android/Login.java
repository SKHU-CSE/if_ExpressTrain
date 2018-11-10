package express.if_week.expresstrain_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;
import java.net.URLConnection;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText etID = findViewById(R.id.login_id_edit);
        EditText etPW = findViewById(R.id.login_pw_edit);

        Button btLogin = findViewById(R.id.btLogin);
        Button btRegister = findViewById(R.id.btRegister);

        btLogin.setOnClickListener((v) -> {
            String id = etID.getText().toString();
            String pw = etPW.getText().toString();
            try {
                URL url = new URL("http://ec2-52-14-45-167.us-east-2.compute.amazonaws.com/");
            }catch (Exception e){
                
            }
    });

        btRegister.setOnClickListener((v)->

    {

    });
}
}
