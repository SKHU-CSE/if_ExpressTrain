package express.if_week.expresstrain_android;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Content_ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView name;
    public TextView time;
    public TextView content;


    public Content_ViewHolder(View view) {
        super(view);

        name = (TextView)view.findViewById(R.id.content_profile_name);
        time = (TextView)view.findViewById(R.id.content_profile_time);
        content = (TextView)view.findViewById(R.id.content_profile_content);
    }
}
