package express.if_week.expresstrain_android;


import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetJson {
    final String myURL = "http://ec2-52-14-45-167.us-east-2.compute.amazonaws.com/";

    private OkHttpClient client;
    private static GetJson instance = new GetJson();

    public static GetJson getInstance() {
        return instance;
    }

    protected GetJson() {
        this.client = new OkHttpClient();
    }


    /**
     * 웹 서버로 요청을 한다.
     */
    public void requestWebServer(Callback callback,String php,String... param) {


        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(myURL)
                .post(body)//받은 데이터
                .build();
        client.newCall(request).enqueue(callback); //통신후 콜백될 함수
    }
}