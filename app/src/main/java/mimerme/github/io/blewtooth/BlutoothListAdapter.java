package mimerme.github.io.blewtooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mimerme.github.io.blewtooth.temp.MyRecyclerViewAdapter;

public class BlutoothListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<BluetoothDevice> mDataset;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public BlutoothListAdapter(Context context, ArrayList<BluetoothDevice> mDataset) {
        this.mDataset = mDataset;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String animal = mDataset.get(position).toString();
        holder.myTextView.setText(animal);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    void setClickListener(MyRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
