package express.if_week.expresstrain_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecycler_stroeView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager_store;
    ArrayList<Store_item> arrayList_store;
    private StoreAdapter store_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView=findViewById(R.id.category_recycle);
        mRecycler_stroeView=findViewById(R.id.store1_cardview);
        mRecyclerView.setHasFixedSize(true);
        mRecycler_stroeView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayout.HORIZONTAL);

        mLayoutManager_store=new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager_store).setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecycler_stroeView.setLayoutManager(mLayoutManager_store);

        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(new String[]{"분식","중식","편의점","제과점","한식","부식"});
        mRecyclerView.setAdapter(mAdapter);
        arrayList_store=new ArrayList<Store_item>();
        store_adapter=new StoreAdapter(arrayList_store);

        mRecycler_stroeView.setAdapter(store_adapter);

        arrayList_store.add(new Store_item("장희승","인천광역시 검단","010-4012-2423"));
        arrayList_store.add(new Store_item("임수현","서울시 검단","010-4012-2423"));
        arrayList_store.add(new Store_item("김남수","서울시 목동","010-4012-2423"));

        //arrayList_store.add()
    }
}
