package express.if_week.expresstrain_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class CardSelect extends AppCompatActivity {

    int selectedID = -1;
    String cardnum = "";
    RelativeLayout dreamTree;
    RelativeLayout dreamJaram;
    RelativeLayout gDream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_select);

        dreamTree = findViewById(R.id.rlDreamtree);
        dreamJaram = findViewById(R.id.rlDreamjaram);
        gDream = findViewById(R.id.rlGdream);

        View.OnClickListener cardClickListener = new CardClick();
        dreamTree.setOnClickListener(cardClickListener);
        dreamJaram.setOnClickListener(cardClickListener);
        gDream.setOnClickListener(cardClickListener);

    }

    public void onClickSelect(View v) {
        int id = v.getId();
        String title = "";
        String message = "선택한 카드의 가맹점 목록을 보시겠습니까?";

        switch (selectedID) {
            case R.id.rlDreamtree:
                title = "꿈나무 카드";
                cardnum = "0";
                break;

            case R.id.rlDreamjaram:
                title = "꿈자람 카드";
                cardnum ="1";
                break;

            case R.id.rlGdream:
                title = "지드림 카드";
                cardnum = "2";
                break;

            default:
                title = "카드를 선택하세요";
                message = "선택한 카드가 없습니다.";
        }


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        final String finalTitle = title;
        alertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {

                                Intent intent = new Intent(CardSelect.this, MainActivity.class);
                                intent.putExtra("cardname", finalTitle);
                                intent.putExtra("cardtype", cardnum);

                                startActivity(intent);
                                CardSelect.this.finish();
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



    }

    class CardClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            int id = v.getId();
            selectedID = id;

            switch (id) {
                case R.id.rlDreamtree:
                    dreamTree.setBackgroundColor(Color.parseColor("#eec25b"));
                    dreamJaram.setBackgroundColor(Color.WHITE);
                    gDream.setBackgroundColor(Color.WHITE);
                    break;

                case R.id.rlDreamjaram:
                    dreamTree.setBackgroundColor(Color.WHITE);
                    dreamJaram.setBackgroundColor(Color.parseColor("#eec25b"));
                    gDream.setBackgroundColor(Color.WHITE);
                    break;

                case R.id.rlGdream:
                    dreamTree.setBackgroundColor(Color.WHITE);
                    dreamJaram.setBackgroundColor(Color.WHITE);
                    gDream.setBackgroundColor(Color.parseColor("#eec25b"));
                    break;
            }

        }
    }
}