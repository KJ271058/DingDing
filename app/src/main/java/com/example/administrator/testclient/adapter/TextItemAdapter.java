package com.example.administrator.testclient.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.testclient.MyApplication;
import com.example.administrator.testclient.R;

public class TextItemAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();

    private Context context;
    private LayoutInflater layoutInflater;

    public TextItemAdapter(List<String> objects) {
        this.objects = objects;
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
            convertView = layoutInflater.inflate(R.layout.text_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder) {
        //TODO implement
        holder.text.setText(object+"");
    }

    protected class ViewHolder {
        private TextView text;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.text);
        }
    }
}
