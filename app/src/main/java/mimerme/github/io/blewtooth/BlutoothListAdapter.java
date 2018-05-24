package mimerme.github.io.blewtooth;

import android.bluetooth.BluetoothAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BlutoothListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<BluetoothDevice> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public BlutoothListAdapter(ArrayList<BluetoothDevice> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).mTextView.setText(mDataset.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
