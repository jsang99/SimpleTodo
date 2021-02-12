package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    List<String> items;
    OnLongClickListener longClickListener;

    public interface OnLongClickListener{
        void onItemLongClick(int position);
    }

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflator to inflate a view
        // wrap it inside a view holder and return it

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        return new ViewHolder(todoView);
    }

    //responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //grab the item at the position
        String item = items.get(position);

        // bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    // container to provide easy access to views that represents each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);

        }

        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {

                    // Notify the listener which position was long pressed.
                    longClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
