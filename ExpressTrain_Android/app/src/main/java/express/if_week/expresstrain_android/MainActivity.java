package express.if_week.expresstrain_android;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.support.v7.widget.CardView;

import com.android.navermap.NMapPOIflagType;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecycler_stroeView;
    private RecyclerView mRecycler_stroeView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager_store;
    private RecyclerView.LayoutManager mLayoutManager_store2;
    ArrayList<Store_item> arrayList_store;
    ArrayList<Store_item> arrayList_store2;
    private StoreAdapter store_adapter;
    private StoreAdapter storeAdapter2;
    private String mJsonString;



    private static final String LOG_TAG = "";
    private static final String TAG_JSON = "";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_LAT = "latitude";
    private static final String TAG_LON = "longitude";
    ScrollView scrollView;
    private GetJson getJson = new GetJson();

    String[] category=new String[]{"한식","중식","분식","제과점","패스트푸드","부식"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollView scrollView=findViewById(R.id.recycle_scroll);
        mRecyclerView=findViewById(R.id.category_recycle);
        mRecycler_stroeView=findViewById(R.id.store1_cardview);
        mRecycler_stroeView2=findViewById(R.id.store1_cardview2);
        mRecyclerView.setHasFixedSize(true);
        mRecycler_stroeView.setHasFixedSize(true);
        mRecycler_stroeView2.setHasFixedSize(false);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayout.HORIZONTAL);
        mLayoutManager_store=new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager_store).setOrientation(LinearLayout.HORIZONTAL);
        mLayoutManager_store2=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecycler_stroeView.setLayoutManager(mLayoutManager_store);
        mRecycler_stroeView2.setLayoutManager(mLayoutManager_store2);
        mRecycler_stroeView2.setNestedScrollingEnabled(false);

       Button button=findViewById(R.id.Main_before);
       button.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this,StartMenu.class);
               startActivity(intent);
               finish();
           }
       });
        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(category,new boolean[]{true,false,false,false,false,false});
        mRecyclerView.setAdapter(mAdapter);



        mRecyclerView.addOnItemTouchListener(
                new RecycleViewItemClickListener(getApplicationContext(), mRecyclerView, new RecycleViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        boolean[] checked= new boolean[]{false,false,false,false,false,false};
                        checked[position]=true;
                        mAdapter=new CategoryAdapter(category,checked);
                        mRecyclerView.setAdapter(mAdapter);
                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                  //      Toast.makeText(getApplicationContext(),position+"번 째 아이템 롱 클릭",Toast.LENGTH_SHORT).show();
                    }
                }));


        mRecycler_stroeView2.addOnItemTouchListener(
                new RecycleViewItemClickListener(getApplicationContext(), mRecyclerView, new RecycleViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final CardView cardView=view.findViewById(R.id.card_2_card);
                        final RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) cardView.getLayoutParams();
                        ValueAnimator animator=null;

                        if(!arrayList_store2.get(position).expend) {
                            arrayList_store2.get(position).expend=true;
                            animator = ValueAnimator.ofInt(layoutParams.height,layoutParams.height+100);
                        }else {
                            arrayList_store2.get(position).expend=false;
                            animator = ValueAnimator.ofInt(layoutParams.height, layoutParams.height-100);
                        }
                        if(animator!=null) {
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int height = (Integer) valueAnimator.getAnimatedValue();
                                    layoutParams.height = (int) height;
                                    cardView.setLayoutParams(layoutParams);
                                }
                            });
                            animator.setDuration(300);
                            animator.start();
                        }

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        //      Toast.makeText(getApplicationContext(),position+"번 째 아이템 롱 클릭",Toast.LENGTH_SHORT).show();

                    }
                }));



        arrayList_store=new ArrayList<Store_item>();
        arrayList_store2=new ArrayList<Store_item>();
        store_adapter=new StoreAdapter(arrayList_store, this, new StoreAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v) {
            }

            @Override
            public void MapOnClick(View v) {
            }
        });

        storeAdapter2=new StoreAdapter(arrayList_store2,this,new StoreAdapter.ButtonClickListener(){

            @Override
            public void ContentOnClick(View v) {
                Intent intent=new Intent(MainActivity.this,StoreContent.class);
                startActivity(intent);

            }

            @Override
            public void MapOnClick(View v) {
                Intent intent=new Intent(MainActivity.this,MyMap.class);
                   startActivity(intent);
            }
        });
        mRecycler_stroeView.setAdapter(store_adapter);
        mRecycler_stroeView2.setAdapter(storeAdapter2);



        arrayList_store.add(new Store_item(1,"장희승","인천광역시 검단","010-4012-2423",null));
        arrayList_store.add(new Store_item(1,"임수현","서울시 검단","010-4012-2423",null));
        arrayList_store.add(new Store_item(1,"김남수","서울시 목동","010-4012-2423",null));
        arrayList_store.add(new Store_item(1,"함진경","서울특별시 여의대방로 43나길 25","010-4012-2423",null));




        new Thread() {
            public void run() {
                // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                getJson.requestWebServer(callback);
            }
        }.start();
        //arrayList_store.add()

    }
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(okhttp3.Call call, IOException e) {
            Log.d(TAG, "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(okhttp3.Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d(TAG, "서버에서 응답한 Body:" + body);
            mJsonString = body;
            getResult();
        }
    };

    private void getResult(){


        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(mJsonString);
                    Log.d("url",mJsonString);
                    int markerId = NMapPOIflagType.PIN;
                    int length = jsonArray.length();
                    String lon=null;
                    String lat=null;



                    for(int i=0;i<length;i++){

                        JSONObject item = jsonArray.getJSONObject(i);

                        String name = item.getString(TAG_NAME);
                        String address = item.getString(TAG_ADDRESS);
                        String phone = item.getString(TAG_PHONE);
                        lon = item.getString(TAG_LON);
                        lat = item.getString(TAG_LAT);


                        arrayList_store2.add(new Store_item(2,name,address,phone,null));



                    }
                        storeAdapter2.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.d(TAG, "getResult : ", e);
                }

            }
        });


    }

    void goTocard(View view){
        Intent intent=new Intent(this, MyMap.class);
        startActivity(intent);

    }

}
