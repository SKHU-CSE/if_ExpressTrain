package express.if_week.expresstrain_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public  class Textview_ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView mTitle;


    public Textview_ViewHolder(View view) {
        super(view);

        mTitle = (TextView)view.findViewById(R.id.category_text);

    }
}
