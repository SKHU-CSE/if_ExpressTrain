package express.if_week.expresstrain_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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
    DBOpenHelper db;
    @Override
    protected void onStart() {
        super.onStart();

        db.open();
        if(db.getautoLogin()==0)
        {
            EditText editText = findViewById(R.id.content_editText);
            editText.setText("댓글을 입력할 수 없습니다. 로그인 필요");
            editText.setFocusable(false);
        }else{
            final EditText editText = findViewById(R.id.content_editText);
            editText.setText("");
            editText.setHint("댓글 입력");
            editText.setFocusable(true);
            editText.post(new Runnable() {
                @Override
                public void run() {
                    editText.setFocusableInTouchMode(true);
                    editText.requestFocus();

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                    imm.showSoftInput(editText,0);

                }
            });
        }

    }

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
        TextView tv=findViewById(R.id.content_Store_name);
        store_name=intent.getStringExtra("STORE_NAME");
        tv.setText(store_name);
        tv=findViewById(R.id.content_Store_address);
        tv.setText(intent.getStringExtra("STORE_Address"));
        tv=findViewById(R.id.content_Store_phone);
        tv.setText(intent.getStringExtra("STORE_Phone"));
        db=new DBOpenHelper(this);
        db.open();
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

        EditText editText=findViewById(R.id.content_editText);
        final EditText finalEditText = editText;
        final EditText finalEditText1 = editText;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId)
                {
                    case EditorInfo.IME_ACTION_GO:
                        if(finalEditText.getText().toString().equals(""))
                            Toast.makeText(StoreContent.this,"입력해주세요",Toast.LENGTH_LONG).show();
                        else
                        {
                            long now=System.currentTimeMillis();
                            final Date date=new Date(now);
                            final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");


                            new Thread() {
                                public void run() {
                                    // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                                    json.requestWebServer(callback3,"addcomment.php","STORE="+store_name,"CONTENT="+ finalEditText1.getText().toString(),
                                    "TIME="+(sdf.format(date)));
                                }
                            }.start();
                        }

                }
                return false;
            }
        });
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



        Button button = findViewById(R.id.Content_before);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(StoreContent.this,MainActivity.
                finish();
            }
        });

        if(db.getautoLogin()==0||db.getCount()==0) {
            editText = findViewById(R.id.content_editText);
            editText.setText("댓글을 입력할 수 없습니다. 로그인 필요");
            editText.setFocusable(false);
        }else{
            editText = findViewById(R.id.content_editText);
            editText.setText("");
            editText.setHint("댓글 입력");
            editText.setFocusable(true);
        }
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

        new Thread() {
            public void run() {
                // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                json.requestWebServer(callback2,"commentview.php","store="+store_name);
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


    private final Callback callback3 = new Callback() {
        @Override
        public void onFailure(okhttp3.Call call, IOException e) {
            Log.d(TAG, "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(okhttp3.Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d(TAG, "서버에서 응답한 Body:" + body);

            new Thread() {
                public void run() {
                    // 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                    json.requestWebServer(callback2,"commentview.php","store="+store_name);
                }
            }.start();
        }
    };
    private final Callback callback2 = new Callback() {
        @Override
        public void onFailure(okhttp3.Call call, IOException e) {
            Log.d(TAG, "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(okhttp3.Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d(TAG, "서버에서 응답한 Body:" + body);

            getResult2(body);
        }
    };
    private void getResult2(String s) {
        try {
            JSONArray jsonArray = new JSONArray(s);

            int length = jsonArray.length();
            String lon = null;
            String lat = null;


            for (int i = 0; i < length; i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString("name");
                String content = item.getString("content");
                String time = item.getString("time");


                arrayList_content.add(new Store_item(4, name, content, time, null));


            }

            StoreContent.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    //Handle UI here
                    storeAdapter2.notifyDataSetChanged();
                }
            });


        } catch (JSONException e) {
            Log.d(TAG, "getResult : ", e);



        }
    }

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

    public void gotoLogin(View view){
        // 로그인창으로
        Intent intent=new Intent(StoreContent.this,Login.class);
        startActivity(intent);
    }

}
