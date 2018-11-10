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

    String[] category = new String[]{"한식", "중식", "분식", "제과점", "패스트푸드", "부식"};
    private int categorynum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollView scrollView = findViewById(R.id.recycle_scroll);
        mRecyclerView = findViewById(R.id.category_recycle);
        mRecycler_stroeView = findViewById(R.id.store1_cardview);
        mRecycler_stroeView2 = findViewById(R.id.store1_cardview2);
        mRecyclerView.setHasFixedSize(true);
        mRecycler_stroeView.setHasFixedSize(true);
        mRecycler_stroeView2.setHasFixedSize(false);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayout.HORIZONTAL);
        mLayoutManager_store = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager_store).setOrientation(LinearLayout.HORIZONTAL);
        mLayoutManager_store2 = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecycler_stroeView.setLayoutManager(mLayoutManager_store);
        mRecycler_stroeView2.setLayoutManager(mLayoutManager_store2);
        mRecycler_stroeView2.setNestedScrollingEnabled(false);

        final String cardnum = getIntent().getStringExtra("cardtype");
        final String cardname = getIntent().getStringExtra("cardname");

        Button btBefore = findViewById(R.id.Main_before);
        btBefore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartMenu.class);
                startActivity(intent);
                finish();
            }
        });

        final Button btCard = findViewById(R.id.Main_card);
        btCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardSelect.class);
                startActivity(intent);
                finish();
            }
        });
        btCard.setText(cardname);

        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(category, new boolean[]{true, false, false, false, false, false});
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnItemTouchListener(
                new RecycleViewItemClickListener(getApplicationContext(), mRecyclerView, new RecycleViewItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        boolean[] checked = new boolean[]{false, false, false, false, false, false};
                        checked[position] = true;
                        categorynum = position;
                        arrayList_store.clear();
                        arrayList_store2.clear();
                        new Thread() {
                            public void run() {
                                // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                                getJson.requestWebServer(callback, "getdb.php", "cardtype=" + cardnum, "categorynum="+categorynum);
                            }
                        }.start();
                        //arrayList_store.add()
                        mAdapter = new CategoryAdapter(category, checked);
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
                        final CardView cardView = view.findViewById(R.id.card_2_card);
                        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) cardView.getLayoutParams();
                        ValueAnimator animator = null;

                        if (!arrayList_store2.get(position).expend) {
                            arrayList_store2.get(position).expend = true;
                            animator = ValueAnimator.ofInt(layoutParams.height, layoutParams.height + 100);
                        } else {
                            arrayList_store2.get(position).expend = false;
                            animator = ValueAnimator.ofInt(layoutParams.height, layoutParams.height - 100);
                        }
                        if (animator != null) {
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


        arrayList_store = new ArrayList<Store_item>();
        arrayList_store2 = new ArrayList<Store_item>();
        store_adapter = new StoreAdapter(arrayList_store, this, new StoreAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, StoreContent.class);
                intent.putExtra("STORE_NAME", arrayList_store2.get(position).title);
                startActivity(intent);
            }

            @Override
            public void MapOnClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, MyMap.class);
                intent.putExtra("STORE_NAME", arrayList_store2.get(position).title);
                intent.putExtra("type","showOne");
                startActivity(intent);
            }
        });

        storeAdapter2 = new StoreAdapter(arrayList_store2, this, new StoreAdapter.ButtonClickListener() {

            @Override
            public void ContentOnClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, StoreContent.class);
                intent.putExtra("STORE_NAME", arrayList_store2.get(position).title);
                intent.putExtra("STORE_Address", arrayList_store2.get(position).Address);
                intent.putExtra("STORE_Phone", arrayList_store2.get(position).phone);
                startActivity(intent);

            }

            @Override
            public void MapOnClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, MyMap.class);
                intent.putExtra("STORE_NAME", arrayList_store2.get(position).title);
                intent.putExtra("type","showOne");
                startActivity(intent);
            }
        });
        mRecycler_stroeView.setAdapter(store_adapter);
        mRecycler_stroeView2.setAdapter(storeAdapter2);




        new Thread() {
            public void run() {
                // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                getJson.requestWebServer(callback, "getdb.php", "cardtype=" + cardnum, "categorynum="+categorynum);
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

    private void getResult() {


        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray jsonArray = new JSONArray(mJsonString);
                    Log.d("url", mJsonString);
                    int markerId = NMapPOIflagType.PIN;
                    int length = jsonArray.length();
                    String lon = null;
                    String lat = null;


                    for (int i = 0; i < length; i++) {

                        JSONObject item = jsonArray.getJSONObject(i);

                        String name = item.getString(TAG_NAME);
                        String address = item.getString(TAG_ADDRESS);
                        String phone = item.getString(TAG_PHONE);
                        lon = item.getString(TAG_LON);
                        lat = item.getString(TAG_LAT);
                        int type=item.getInt("store_type");

                        if(type==0)
                        arrayList_store2.add(new Store_item(2, name, address, phone, null));
                        else
                            arrayList_store.add(new Store_item(1, name, address, phone, null));

                    }
                    storeAdapter2.notifyDataSetChanged();
                    store_adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.d(TAG, "getResult : ", e);
                }

            }
        });


    }

    void goTocard(View view) {
        Intent intent = new Intent(MainActivity.this, CardSelect.class);
        startActivity(intent);

    }

}