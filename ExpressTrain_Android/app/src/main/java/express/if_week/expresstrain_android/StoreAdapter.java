package express.if_week.expresstrain_android;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Store_item> mDataset;
    private Activity activity;
    ButtonClickListener listener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder



    // Provide a suitable constructor (depends on the kind of dataset)
    public StoreAdapter(ArrayList<Store_item> myDataset, Activity activity,ButtonClickListener listener) {
        mDataset = myDataset;
        this.activity=activity;
        this.listener=listener;
    }


    // Replace the contents of a view (invoked by the layout manager)

    public interface ButtonClickListener {

        void ContentOnClick(View v,int position);

        void MapOnClick(View v,int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.my_category_list, viewGroup, false);
                viewHolder = new Textview_ViewHolder(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.store_cardview, viewGroup, false);
                viewHolder = new VIewHolder2(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.stroe_cardview_type2, viewGroup, false);
                viewHolder = new ViewHolder3(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.content_image, viewGroup, false);
                viewHolder = new Image_ViewHolder(v4);
                break;
            default:
                View v5 = inflater.inflate(R.layout.comtent_profile, viewGroup, false);
                viewHolder = new Content_ViewHolder(v5);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                Textview_ViewHolder vh1 = (Textview_ViewHolder) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case 1:
                VIewHolder2 vh2 = (VIewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            case 2:
                ViewHolder3 vh = (ViewHolder3) viewHolder;
                configureViewHolder3(vh, position);
                break;

            case 3:  // image하나 메모
                Image_ViewHolder vh3 = (Image_ViewHolder) viewHolder;
                configureImage_ViewHolder(vh3, position);
                break;
            case 4:  // content
                Content_ViewHolder vh4=(Content_ViewHolder)viewHolder;
                configureContent_ViewHolder(vh4,position);
        }
    }


    public void addMenu(Store_item item)
    {
        mDataset.add(item);
    }
    @Override
    public int getItemViewType(int position) {

        return mDataset.get(position).Type;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    private void configureViewHolder3(ViewHolder3 vh, final int position) {
        vh.mAddress.setText(mDataset.get(position).getAddress());
        vh.mTitle.setText(mDataset.get(position).getTitle());
        vh.phone.setText(mDataset.get(position).getPhone());

        vh.bContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ContentOnClick(v,position);
            }
        });
        vh.bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.MapOnClick(v,position);
            }
        });

    }

    private void configureContent_ViewHolder(Content_ViewHolder vh1, int position) {
        vh1.content.setText(mDataset.get(position).getAddress());
        vh1.name.setText(mDataset.get(position).getTitle());
        vh1.time.setText(mDataset.get(position).getPhone());
    }
    private void configureImage_ViewHolder(Image_ViewHolder vh1, int position) {
        vh1.mTitle.setImageBitmap(mDataset.get(position).bitmap);
    }

    private void configureViewHolder1(Textview_ViewHolder vh1, int position) {
        vh1.mTitle.setText(mDataset.get(position).getTitle());
    }

    private void configureViewHolder2(VIewHolder2 vh2,int position) {
        vh2.mAddress.setText(mDataset.get(position).getAddress());
        vh2.mTitle.setText(mDataset.get(position).getTitle());
        vh2.phone.setText(mDataset.get(position).getPhone());
    }
}

