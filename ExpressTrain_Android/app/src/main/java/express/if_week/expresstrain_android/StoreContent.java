package express.if_week.expresstrain_android;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class StoreContent extends AppCompatActivity {

    private RecyclerView mRecycler_menu;
    private RecyclerView mRecycler_content;

    private RecyclerView.LayoutManager mLayoutManager_menu;
    private RecyclerView.LayoutManager mLayoutManager_content;

    ArrayList<Store_item> arrayList_menu;
    ArrayList<Store_item> arrayList_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_content);


        mRecycler_menu=findViewById(R.id.content_menu_image);
        mRecycler_content=findViewById(R.id.content_another);

        mRecycler_menu.setHasFixedSize(true);
        mRecycler_content.setHasFixedSize(false);
        // use a linear layout manager
        mLayoutManager_menu = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager_menu).setOrientation(LinearLayout.HORIZONTAL);
        mLayoutManager_content=new LinearLayoutManager(this);

     arrayList_menu=new ArrayList<Store_item>();
      arrayList_content=new ArrayList<Store_item>();
        mRecycler_menu.setLayoutManager(mLayoutManager_menu);
        mRecycler_content.setLayoutManager(mLayoutManager_content);
        mRecycler_content.setNestedScrollingEnabled(false);


        mRecycler_menu.setAdapter(new StoreAdapter(arrayList_menu,this, new StoreAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v) {
            }

            @Override
            public void MapOnClick(View v) {
            }
        }));

        mRecycler_content.setAdapter(new StoreAdapter(arrayList_content,this, new StoreAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v) {
            }

            @Override
            public void MapOnClick(View v) {
            }
        }));


        arrayList_menu.add(new Store_item(3,"","","",BitmapFactory.decodeResource(getResources(),R.drawable.test1)));
        arrayList_menu.add(new Store_item(3,"","","",BitmapFactory.decodeResource(getResources(),R.drawable.test5)));
        arrayList_menu.add(new Store_item(3,"","","",BitmapFactory.decodeResource(getResources(),R.drawable.test2)));
        arrayList_menu.add(new Store_item(3,"","","",BitmapFactory.decodeResource(getResources(),R.drawable.test4)));
        arrayList_menu.add(new Store_item(3,"","","",BitmapFactory.decodeResource(getResources(),R.drawable.test2)));
        arrayList_menu.add(new Store_item(3,"","","",BitmapFactory.decodeResource(getResources(),R.drawable.test1)));


        arrayList_content.add(new Store_item(4,"장희승","ㅇㅁㅈㅇㅁㅈㅇ","2018.11.7",null));
        arrayList_content.add(new Store_item(4,"함진경","젤리 조아","2018.11.8",null));
        arrayList_content.add(new Store_item(4,"임수현","글자 수를 테스트해보자 ㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅇㅁㅇㅁㅇㅇㅁㅇㅁㅇㅁㅇ","2018.11.9",null));
        arrayList_content.add(new Store_item(4,"양민욱","순조롭다~~","2018.11.10",null));
        arrayList_content.add(new Store_item(4,"김남수","ㅎㅎㅎㅎ","2018.11.11",null));

        Button button=findViewById(R.id.Content_before);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(StoreContent.this,MainActivity.
                finish();
            }
        });
    }
}
