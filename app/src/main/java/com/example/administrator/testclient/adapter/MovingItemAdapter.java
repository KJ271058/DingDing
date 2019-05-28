package com.example.administrator.testclient.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.administrator.testclient.MyApplication;
import com.example.administrator.testclient.R;
import com.example.administrator.testclient.util.CommonUtil;

public class MovingItemAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();

    private Context context;
    private LayoutInflater layoutInflater;

    public MovingItemAdapter(List list) {
        this.objects = list;
        this.context = MyApplication.getContext();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.moving_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String) getItem(position), (ViewHolder) convertView.getTag(),position+1);
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder, int position) {
        //TODO implement
        holder.name.setText(object+"");
        holder.attention.setChecked(position%2==0);

        Glide.with(MyApplication.getContext()).load(object).placeholder(R.mipmap.test_error).error(R.mipmap.test_error).into(holder.img1);

        holder.landNum.setText(position+"");
        holder.commentNum.setText(position+5+"");
        holder.laud.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int x =Integer.parseInt(holder.landNum.getText().toString());
            if (isChecked){
                x++;
            }else {
                x--;
            }
            holder.landNum.setText(x+"");
        });
    }

    protected class ViewHolder {
        private CircleImageView heard;
        private TextView name;
        private TextView time;
        private ToggleButton attention;
        private TextView data;
        private ImageView img1;
        private ImageView img2;
        private ImageView img3;
        private ImageView img4;
        private CheckBox laud;
        private TextView landNum;
        private ImageView comment;
        private TextView commentNum;

        public ViewHolder(View view) {
            heard = (CircleImageView) view.findViewById(R.id.heard);
            name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            attention = (ToggleButton) view.findViewById(R.id.attention);
            data = (TextView) view.findViewById(R.id.data);
            img1 = (ImageView) view.findViewById(R.id.img1);
            img2 = (ImageView) view.findViewById(R.id.img2);
            img3 = (ImageView) view.findViewById(R.id.img3);
            img4 = (ImageView) view.findViewById(R.id.img4);
            laud = (CheckBox) view.findViewById(R.id.laud);
            landNum = (TextView) view.findViewById(R.id.land_num);
            comment = (ImageView) view.findViewById(R.id.comment);
            commentNum = (TextView) view.findViewById(R.id.comment_num);
        }
    }
}
