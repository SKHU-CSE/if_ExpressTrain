package express.if_week.expresstrain_android;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public  class Image_ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public ImageView mTitle;


    public Image_ViewHolder(View view) {
        super(view);

        mTitle = (ImageView)view.findViewById(R.id.content_image_image);

    }
}
