package express.if_week.expresstrain_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.navermap.NMapPOIflagType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class StoreContent extends AppCompatActivity {

    private RecyclerView mRecycler_menu;
    private RecyclerView mRecycler_content;

    private RecyclerView.LayoutManager mLayoutManager_menu;
    private RecyclerView.LayoutManager mLayoutManager_content;
    Bitmap selectBitmap;
    ArrayList<Store_item> arrayList_menu;
    ArrayList<Store_item> arrayList_content;
    String store_name;
    GetJson json;
    String mJsonString;
    StoreAdapter storeAdapter1;
    StoreAdapter storeAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_content);

        Intent intent = getIntent();
        store_name = intent.getStringExtra("STORE_NAME");
        mRecycler_menu = findViewById(R.id.content_menu_image);
        mRecycler_content = findViewById(R.id.content_another);

        mRecycler_menu.setHasFixedSize(true);
        mRecycler_content.setHasFixedSize(false);
        // use a linear layout manager
        mLayoutManager_menu = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager_menu).setOrientation(LinearLayout.HORIZONTAL);

        mLayoutManager_content=new LinearLayoutManager(this);
        json=new GetJson();
     arrayList_menu=new ArrayList<Store_item>();
      arrayList_content=new ArrayList<Store_item>();

        mRecycler_menu.setLayoutManager(mLayoutManager_menu);
        mRecycler_content.setLayoutManager(mLayoutManager_content);
        mRecycler_content.setNestedScrollingEnabled(false);


        storeAdapter1=new StoreAdapter(arrayList_menu,this, new StoreAdapter.ButtonClickListener() {



            @Override
            public void ContentOnClick(View v, int position) {
            }

            @Override
            public void MapOnClick(View v, int position) {
            }
        });
        mRecycler_menu.setAdapter(storeAdapter1);


        storeAdapter2=new StoreAdapter(arrayList_content,this, new StoreAdapter.ButtonClickListener() {


            @Override
            public void ContentOnClick(View v, int position) {
            }

            @Override
            public void MapOnClick(View v, int position) {
            }
        });
        mRecycler_content.setAdapter(storeAdapter2);




        arrayList_content.add(new Store_item(4, "장희승", "ㅇㅁㅈㅇㅁㅈㅇ", "2018.11.7", null));
        arrayList_content.add(new Store_item(4, "함진경", "젤리 조아", "2018.11.8", null));
        arrayList_content.add(new Store_item(4, "임수현", "글자 수를 테스트해보자 ㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅁㅇㅇㅁㅇㅁㅇㅇㅁㅇㅁㅇㅁㅇ", "2018.11.9", null));
        arrayList_content.add(new Store_item(4, "양민욱", "순조롭다~~", "2018.11.10", null));
        arrayList_content.add(new Store_item(4, "김남수", "ㅎㅎㅎㅎ", "2018.11.11", null));

        Button button = findViewById(R.id.Content_before);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(StoreContent.this,MainActivity.
                finish();
            }
        });

        Button button1 = findViewById(R.id.content_menu_add);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), 1);
            }
        });

        new Thread() {
            public void run() {
                // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                json.requestWebServer(callback,"menuView.php","store="+store_name);
            }
        }.start();
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
         try{
        JSONArray jsonArray = new JSONArray(mJsonString);
        Log.d("url", mJsonString);
        int markerId = NMapPOIflagType.PIN;
        int length = jsonArray.length();
        String lon = null;
        String lat = null;


        for (int i = 0; i < length; i++) {

            JSONObject item = jsonArray.getJSONObject(i);

            String menu = item.getString("MENU_IMG");

            //  URL url=new URL(menu.replace("\\",""));
            URL url=new URL(menu);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is=conn.getInputStream();

            storeAdapter1.addMenu(new Store_item(3, "", "", "", BitmapFactory.decodeStream(is)));




        }

             StoreContent.this.runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     //Handle UI here
                     storeAdapter1.notifyDataSetChanged();
                 }
             });



         } catch (JSONException e) {
        Log.d(TAG, "getResult : ", e);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

        Handler handler = new Handler(Looper.getMainLooper());


    }



        @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

                Uri selectedImageUri = data.getData();
                try {
                    selectBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    uploadImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = ProgressDialog.show(StoreContent.this, "Uploading...", null, true, true);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);


                HashMap<String, String> data = new HashMap<>();

                data.put("image", uploadImage);//php에서 POST값으로 들어감
                data.put("store", store_name);


/*
                long now=System.currentTimeMillis();
                Date date=new Date(now);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

                data.put("time",sdf.format(date));

                */
                String result = rh.sendPostRequest("http://ec2-52-14-45-167.us-east-2.compute.amazonaws.com/menuinsert.php", data);


                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(selectBitmap);
    }

}
