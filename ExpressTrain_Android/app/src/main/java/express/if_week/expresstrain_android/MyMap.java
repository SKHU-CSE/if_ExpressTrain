package express.if_week.expresstrain_android;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;
import com.android.navermap.*;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

public class MyMap extends NMapActivity{
    private NMapView mMapView;// 지도 화면 View
    private final String CLIENT_ID = "N9b68ZJe0OThPeeDBC_b";// 애플리케이션 클라이언트 아이디 값

    // create resource provider
    NMapViewerResourceProvider mMapViewerResourceProvider=null;
    NMapOverlayManager mOverlayManager=null;

    private static final String LOG_TAG = "MyMap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);

        mMapView = new NMapView(this);
        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();

        ViewGroup vg = findViewById(R.id.mymap);
        vg.addView(mMapView);

        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

        int markerId = NMapPOIflagType.PIN;

        //NMapPOIdataOverlay 객체 생성 - 여러개의 오버레이 아이템들을 하나의 오버레이 객체로 관리하기 위해
            // set POI data
        NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
        poiData.beginPOIdata(2);
        poiData.addPOIitem(127.0630205, 37.5091300, "Pizza 777-111", markerId, 0);
        poiData.addPOIitem(127.061, 37.51, "Pizza 123-456", markerId, 0);
        poiData.endPOIdata();
            // create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);


        // 해당 오버레이 객체에 포함된 전체 아이템이 화면에 표시되도록 지도 중심 및 축적 레벨을 변경하려면 아래와 같이 구현합니다.
            // show all POI data
        poiDataOverlay.showAllPOIdata(0);
    }

    public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
        // [[TEMP]] handle a click event of the callout
        Toast.makeText(MyMap.this, "onCalloutClick: " + item.getTitle(), Toast.LENGTH_LONG).show();
    }

    public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
        if (item != null) {
            Log.i(LOG_TAG, "onFocusChanged: " + item.toString());
        } else {
            Log.i(LOG_TAG, "onFocusChanged: ");
        }
    }
}
