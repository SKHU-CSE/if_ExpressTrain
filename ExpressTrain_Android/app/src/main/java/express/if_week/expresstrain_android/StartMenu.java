package express.if_week.expresstrain_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class StartMenu extends AppCompatActivity {


    DBOpenHelper db;
    CardView map;
    CardView store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);


        db=new DBOpenHelper(this);
        db.open();

        if(db.getautoLogin()==1)
            db.UpdateAuto(0);

        map = findViewById(R.id.start_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartMenu.this,MyMap.class);
                intent.putExtra("type","showAll");

                startActivity(intent);
                finish();
            }
        });
        store=findViewById(R.id.start_store);
        store.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartMenu.this,MainActivity.class);
                intent.putExtra("cardtype","0");
                intent.putExtra("cardname","꿈나무 카드");
                startActivity(intent);
                finish();
            }
        });
    }
}
