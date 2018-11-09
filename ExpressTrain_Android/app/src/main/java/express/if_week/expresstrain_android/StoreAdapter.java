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

        void ContentOnClick(View v);

        void MapOnClick(View v);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.my_category_list, viewGroup, false);
                viewHolder = new ViewHolder(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.store_cardview, viewGroup, false);
                viewHolder = new VIewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.stroe_cardview_type2, viewGroup, false);
                viewHolder = new ViewHolder3(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                ViewHolder vh1 = (ViewHolder) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case 1:
                VIewHolder2 vh2 = (VIewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            default:
                ViewHolder3 vh = (ViewHolder3) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).Type==0) {
            return 0;
        } else if (mDataset.get(position).Type==1) {
            return 1;
        }
        return -1;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    private void configureDefaultViewHolder(ViewHolder3 vh, int position) {
        vh.mAddress.setText(mDataset.get(position).getAddress());
        vh.mTitle.setText(mDataset.get(position).getTitle());
        vh.phone.setText(mDataset.get(position).getPhone());

        vh.bContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ContentOnClick(v);
            }
        });
        vh.bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.MapOnClick(v);
            }
        });

    }

    private void configureViewHolder1(ViewHolder vh1, int position) {
        vh1.mTitle.setText(mDataset.get(position).getTitle());
    }

    private void configureViewHolder2(VIewHolder2 vh2,int position) {
        vh2.mAddress.setText(mDataset.get(position).getAddress());
        vh2.mTitle.setText(mDataset.get(position).getTitle());
        vh2.phone.setText(mDataset.get(position).getPhone());
    }
}

