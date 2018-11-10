package express.if_week.expresstrain_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartMenu extends AppCompatActivity {

    android.support.v7.widget.CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);


        cardView=findViewById(R.id.start_store);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartMenu.this,MainActivity.class);
                intent.putExtra("cardtype","0");
                startActivity(intent);
                finish();
            }
        });
    }
}
