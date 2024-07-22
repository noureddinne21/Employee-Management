package com.nouroeddinne.roomdatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViweHolder>{

    MyViewModel myViewModel;
    private Context context;
    private List<Employe> employeeList;


    public EmployeeAdapter(Context context, List<Employe> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employe, parent, false);
        return new ViweHolder(viwe);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViweHolder holder, int position) {

        myViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MyViewModel.class);

        Employe e = employeeList.get(position);
        holder.textName.setText(e.getName());
        holder.texttitle.setText(e.getTitle());
        if (e.getGender().equals("Man")){
            holder.img.setImageResource(R.drawable.man);
        }else {
            holder.img.setImageResource(R.drawable.businesswoman);
        }
        holder.textdate.setText(DateCoverter.handleSelectedDate(e.getDatebirth()));
        holder.textid.setText(String.valueOf(position+1));
        holder.statusEmployeee = e.getStatus();

        if (holder.statusEmployeee){
            holder.textstatus.setText("Active");
            holder.textstatus.setTextColor(ContextCompat.getColor(context, R.color.green));
        }else {
            holder.textstatus.setText("Inactive");
            holder.textstatus.setTextColor(ContextCompat.getColor(context, R.color.red));
        }


        holder.imgmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                PopupMenu popup = new PopupMenu(v.getContext(), holder.imgmore);
                popup.inflate(R.menu.menu);

// Set the title based on the current statusEmployeee
                MenuItem statusItem = popup.getMenu().findItem(R.id.action_status);
                statusItem.setTitle(!holder.statusEmployeee ? "Active" : "Inactive");
                statusItem.setChecked(holder.statusEmployeee);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menu1) {
                            // Handle menu item 1 click (Example: Open employee info activity)
                            Intent intent = new Intent(context, EmployeInfoActivity.class);
                            intent.putExtra("employee", e);
                            context.startActivity(intent);
                            return true;
                        } else if (item.getItemId() == R.id.action_status) {
                            // Toggle the statusEmployeee variable
                            holder.statusEmployeee = !holder.statusEmployeee;
                            item.setChecked(holder.statusEmployeee);

                            // Update the employee status in your ViewModel or repository
                            e.setStatus(holder.statusEmployeee);
                            myViewModel.updatetEmploy(e);

                            // Update the title based on the new status
                            item.setTitle(holder.statusEmployeee ? "Active" : "Inactive");

                            // Notify the adapter that data has changed
                            notifyDataSetChanged();
                            return true;
                        }
                        return false;
                    }
                });

                popup.show();








//                PopupMenu popup = new PopupMenu(v.getContext(), holder.imgmore);
//                popup.inflate(R.menu.menu);
//                popup.getMenu().findItem(R.id.action_status).setChecked(statusEmployeee);
////                popup.getMenu().findItem(R.id.action_statusEmployeee).setCheckable(true);
//                popup.getMenu().findItem(R.id.action_status).setTitle("Active");
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if (item.getItemId()==R.id.menu1){
//                            Intent intent = new Intent(context,EmployeInfoActivity.class);
//                            intent.putExtra("employee",e);
//                            context.startActivity(intent);
//                        }else if (item.getItemId()==R.id.action_status){
//
//                            Toast.makeText(context, ""+statusEmployeee, Toast.LENGTH_SHORT).show();
//                            statusEmployeee= !statusEmployeee;
//                            Toast.makeText(context, ""+statusEmployeee, Toast.LENGTH_SHORT).show();
//                            item.setChecked(statusEmployeee);
//                            e.setStatus(statusEmployeee);
//                            myViewModel.updatetEmploy(e);
//                            notifyDataSetChanged();
//
//                        }
//                        return false;
//                    }
//                });
//                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder{

        boolean statusEmployeee;
        TextView textName,texttitle,textdate,textphone,textemail,textstatus,textid;
        ImageView img,imgmore;
        public ViweHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textView_name);
            texttitle = itemView.findViewById(R.id.textView_title);
            textdate = itemView.findViewById(R.id.textView_date);
            textstatus = itemView.findViewById(R.id.textView_status);
            textid = itemView.findViewById(R.id.textView_index);

            img = itemView.findViewById(R.id.imageView_img);
            imgmore = itemView.findViewById(R.id.imageView_more);

        }







    }




























}
