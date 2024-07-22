package com.nouroeddinne.roomdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.ViweHolderSalary>{

    private Context context;
    private List<Salary> SalaryList;
    public SalaryAdapter(Context context,  List<Salary> SalaryList) {
        this.context = context;
        this.SalaryList = SalaryList;
    }

    @NonNull
    @Override
    public ViweHolderSalary onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salary, parent, false);
        return new SalaryAdapter.ViweHolderSalary(viwe);    }

    @Override
    public void onBindViewHolder(@NonNull SalaryAdapter.ViweHolderSalary holder, int position) {
        Salary s = SalaryList.get(position);
        holder.textinfo.setText(s.getInformationPayment());
        holder.textsalary.setText(String.valueOf(s.getSalary()));
        holder.textdate.setText(DateCoverter.handleSelectedDate(s.getDatePayment()));
    }

    @Override
    public int getItemCount() {
        return SalaryList.size();
    }

    public class ViweHolderSalary extends RecyclerView.ViewHolder{

        TextView textinfo,textsalary,textdate;
        public ViweHolderSalary(@NonNull View itemView) {
            super(itemView);

            textinfo = itemView.findViewById(R.id.textView2);
            textdate = itemView.findViewById(R.id.textView4);
            textsalary = itemView.findViewById(R.id.textView3);


        }
    }



















}

