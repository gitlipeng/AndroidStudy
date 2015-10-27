package com.android.study;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.study.adapter.SimpleArrayAdapter;
import com.android.study.imagetest.HandViewActivity;
import com.android.study.imagetest.RoundConcerActivity;
import com.android.study.utils.BaseUtils;

/**
 * Created by user on 2015/10/27.
 */
public class ImageActivity extends Activity implements ListView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ListView list = new ListView(getApplicationContext());
        LauncherAdapter adapter = new LauncherAdapter(getApplicationContext());
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        setContentView(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class activityInfo = (Class) parent.getAdapter().getItem(position);
        Intent intent = new Intent(getApplicationContext(), activityInfo);
        startActivity(intent);
    }

    private static class LauncherAdapter extends SimpleArrayAdapter<Class> {
        private LayoutInflater inflater;
        private Context context;
        public LauncherAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            this.context = context;
//            try {
//                PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.android.study.imagetest", 1);
//                ActivityInfo[] activityInfos = packageInfo.activities;
//                for (ActivityInfo activityInfo : activityInfos) {
//                    if (activityInfo.name.contains(MainActivity.class.getName()) && TextUtils.isEmpty(activityInfo.nonLocalizedLabel)) {
//                        continue;
//                    }
//
//                    addItem(activityInfo);
//                }
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
            addItem(HandViewActivity.class);
            addItem(RoundConcerActivity.class);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Class activityInfo = getItem(position);
            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView txt = BaseUtils.getViewByHolder(convertView, android.R.id.text1);
            txt.setBackgroundColor(context.getResources().getColor(R.color.FB4747));
            txt.setTextColor(Color.WHITE);
            String label = activityInfo.getName().toString();
            txt.setText(label.substring(label.lastIndexOf(".") + 1));
            return convertView;
        }
    }
}
