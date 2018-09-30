package com.example.cortex.talktoserver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cortex.talktoserver.R;
import com.example.cortex.talktoserver.model.User;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context c;
    ArrayList<User> arrayList;


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.activity_user,null);
       //TextView tvUserFirstName = customView.findViewById(R.id.tvUserFirstName);
        TextView tvUserLastName = customView.findViewById(R.id.tvUserLastName);
        TextView tvUserAge = customView.findViewById(R.id.tvUserAge);
        /*ImageView imageUser = customView.findViewById(R.id.gridImage);
        TextView textUser = customView.findViewById(R.id.textGridName);

        imageUser.setImageResource(arrayList.get(position).getImageID());
        textUser.setText(arrayList.get(position).getName());*/
      // tvUserFirstName.setText(arrayList.get(position).getUserFirstName());
        tvUserLastName.setText(arrayList.get(position).getUserFirstName()+" "+arrayList.get(position).getUserLastName());
        tvUserAge.setText(arrayList.get(position).getUserAge());
        return customView;
    }

    public UserAdapter(Context c, ArrayList<User> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }
    public void RemoveAll(){
        arrayList.clear();
    }
}
