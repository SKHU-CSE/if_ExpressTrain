package express.if_week.expresstrain_android;

import android.graphics.Typeface;
import android.support.v4.graphics.TypefaceCompatApi26Impl;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private String[] mDataset;
    private boolean[] mClicked;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(String[] myDataset,boolean[] checked) {
        mDataset = myDataset;
        mClicked=checked;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_category_list, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);
        if(mClicked[position]) {
            holder.mTextView.setTypeface(null, Typeface.BOLD);
            holder.mTextView.setTextColor(0xeec25b);
        }

    }

    public void reset(String[] arr)
    {
        mDataset=arr;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
