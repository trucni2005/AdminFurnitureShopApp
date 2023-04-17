package com.example.adminfunitureshopapp.model.Account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adminfunitureshopapp.R;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.Viewholder> {

    private static List<Account> accounts;

    public AccountAdapter(List<Account> accountList) {
        this.accounts = accountList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_item, parent, false);
        return new AccountAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.Viewholder holder, int position) {
        Account account = accounts.get(position);
        holder.id.setText(String.valueOf(account.getId()));
        holder.name.setText(account.getUsername());
        holder.role.setText(account.getRole());
        holder.phone.setText(account.getPhone());
        if (account.getRole().equals("admin")) holder.imageUrl.setImageResource(R.drawable.admin_icon);
            else holder.imageUrl.setImageResource(R.drawable.male_user);
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        public ImageView imageUrl;
        public TextView id;
        public TextView name;
        public TextView role;
        public TextView phone;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageUrl = itemView.findViewById(R.id.img_account);
            id = itemView.findViewById(R.id.tv_Acccount_ID);
            role = itemView.findViewById(R.id.tv_Acccount_role);
            name = itemView.findViewById(R.id.tv_Acccount_name);
            phone = itemView.findViewById(R.id.tv_Acccount_phone);
        }
    }
}
