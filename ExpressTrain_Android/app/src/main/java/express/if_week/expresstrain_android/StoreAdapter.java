package express.if_week.expresstrain_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private ArrayList<Store_item> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitle;
        public TextView address;
        public TextView phone;

        public ViewHolder(View view) {
            super(view);

            mTitle = (TextView)view.findViewById(R.id.card_title);
            address=(TextView)view.findViewById(R.id.card_address);
            phone=(TextView)view.findViewById(R.id.card_phone);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StoreAdapter(ArrayList<Store_item> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitle.setText(mDataset.get(position).getTitle());
        holder.address.setText(mDataset.get(position).getAddress());
        holder.phone.setText(mDataset.get(position).getPhone());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

