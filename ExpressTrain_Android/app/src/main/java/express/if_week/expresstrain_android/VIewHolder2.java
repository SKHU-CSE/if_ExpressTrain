package express.if_week.expresstrain_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class VIewHolder2 extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView mTitle;
    public TextView mAddress;
    public TextView phone;


    public VIewHolder2(View view) {
        super(view);


        mTitle = (TextView)view.findViewById(R.id.card_title);
        mAddress=(TextView)view.findViewById(R.id.card_address);
        phone=view.findViewById(R.id.card_phone);
    }
}