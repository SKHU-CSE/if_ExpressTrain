package express.if_week.expresstrain_android;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHolder3 extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView mTitle;
    public TextView mAddress;
    public TextView phone;
    public Button bMap;
    public Button bContent;

    public ViewHolder3(View view) {
        super(view);


        mTitle = (TextView)view.findViewById(R.id.card_2_title);
        mAddress=(TextView)view.findViewById(R.id.card_2_address);
        phone=view.findViewById(R.id.card_2_phone);
        bMap=view.findViewById(R.id.card_2_showmap);
        bContent=view.findViewById(R.id.card_2_showcontent);

    }
}