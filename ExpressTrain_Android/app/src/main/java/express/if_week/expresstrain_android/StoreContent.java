package express.if_week.expresstrain_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StoreContent extends AppCompatActivity {

    private RecyclerView mRecycler_menu;
    private RecyclerView mRecycler_content;

    private RecyclerView.LayoutManager mLayoutManager_menu;
    private RecyclerView.LayoutManager mLayoutManager_content;
    Bitmap selectBitmap;
    ArrayList<Store_item> arrayList_menu;
    ArrayList<Store_item> arrayList_content;
    String store_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_content);

        Intent intent=getIntent();
        store_name=intent.getStringExtra("STORE_NAME");
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
            public void ContentOnClick(View v,int position) {
            }

            @Override
            public void MapOnClick(View v,int position) {
            }
        }));

        mRecycler_content.setAdapter(new StoreAdapter(arrayList_content,this, new StoreAdapter.ButtonClickListener() {
            @Override
            public void ContentOnClick(View v,int position) {
            }

            @Override
            public void MapOnClick(View v,int position) {
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

        Button button1=findViewById(R.id.content_menu_add);
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
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            if (requestCode == 1&&resultCode==RESULT_OK&&data !=null&&data.getData() !=null) {

                Uri selectedImageUri = data.getData();
                try{
                    selectBitmap =MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);
                    uploadImage();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageBytes =baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = ProgressDialog.show(StoreContent.this, "Uploading...", null,true,true);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);


                HashMap<String,String> data = new HashMap<>();

                data.put("image", uploadImage);//php에서 POST값으로 들어감
                data.put("store",store_name);


/*
                long now=System.currentTimeMillis();
                Date date=new Date(now);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

                data.put("time",sdf.format(date));

                */
                String result = rh.sendPostRequest("http://ec2-52-14-45-167.us-east-2.compute.amazonaws.com/menuinsert.php",data);


                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(selectBitmap);
    }

}
