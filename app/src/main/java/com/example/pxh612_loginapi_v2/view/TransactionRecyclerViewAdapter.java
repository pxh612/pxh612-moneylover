package com.example.pxh612_loginapi_v2.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pxh612_loginapi_v2.R;
import com.example.pxh612_loginapi_v2.model.Transaction;

import java.util.ArrayList;

public class TransactionRecyclerViewAdapter extends RecyclerView.Adapter<TransactionRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Transaction> transactions = new ArrayList<>();


    public interface TransactionRecyclerViewAdapterListener {
        void onRecycleViewItemClick();
    }

    ;
    TransactionRecyclerViewAdapterListener listener;

    public TransactionRecyclerViewAdapter(ArrayList<Transaction> transactions, TransactionRecyclerViewAdapterListener listener) {
        this.transactions = transactions;
        this.listener = listener;
    }


    @NonNull
    @Override
    public TransactionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_recyclerview_viewholder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.amount.setText(transactions.get(position).getAmountString());
        holder.cate.setText(transactions.get(position).getCategory());
        holder.date.setText(transactions.get(position).getDateString());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // XML
        TextView amount;
        TextView cate;
        TextView date;

        public ViewHolder(View v) {
            super(v);

            // XML: ATTACH VIEW
            amount = v.findViewById(R.id.amount);
            cate = v.findViewById(R.id.cate);
            date = v.findViewById(R.id.date);

            // ON SCREEN CLICK
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    int deckID = deckIDs.get(position);
//                    listener.onRecycleViewItemClick(deckID);
                }
            });
        }
    }

    public int getTransactionID(int recyclerviewItemPosition) {
        return transactions.get(recyclerviewItemPosition).getId();
    }

    public void removeItem(int recyclerviewItemPosition) {
        transactions.remove(recyclerviewItemPosition);
    }

}
