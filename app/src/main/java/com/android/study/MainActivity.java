package com.android.study;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.study.adapter.SimpleArrayAdapter;
import com.android.study.utils.BaseUtils;


public class MainActivity extends Activity implements ListView.OnItemClickListener {

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
        try {
            ActivityInfo activityInfo = (ActivityInfo) parent.getAdapter().getItem(position);
            Intent intent = new Intent(getApplicationContext(), Class.forName(activityInfo.name));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class LauncherAdapter extends SimpleArrayAdapter<ActivityInfo> {
        private LayoutInflater inflater;
        private Context context;
        public LauncherAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            this.context = context;
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
                ActivityInfo[] activityInfos = packageInfo.activities;
                for (ActivityInfo activityInfo : activityInfos) {
                    if (activityInfo.name.contains(MainActivity.class.getName()) || TextUtils.isEmpty(activityInfo.nonLocalizedLabel)) {
                        continue;
                    }
                    addItem(activityInfo);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ActivityInfo activityInfo = getItem(position);
            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView txt = BaseUtils.getViewByHolder(convertView, android.R.id.text1);
            txt.setBackgroundColor(context.getResources().getColor(R.color.FB4747));
            txt.setTextColor(Color.WHITE);
            CharSequence label = activityInfo.nonLocalizedLabel;
            if (TextUtils.isEmpty(label)) {
                txt.setText(activityInfo.name.substring(activityInfo.name.lastIndexOf(".") + 1));
            } else {
                txt.setText(label);
            }
            return convertView;
        }
    }
}
