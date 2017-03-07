package com.adem.ListPostMain;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 3/7/2017.
 */

public class NewsAdapter extends ArrayAdapter<News>{

    private Activity context;
    private int resource;
    private ArrayList<News> model;

    public NewsAdapter(Activity context, int resource, ArrayList<News> model) {
        super(context, resource, model);

        this.context = context;
        this.resource = resource;
        this.model = model;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        NewsHolder newsHolder = null;

        if (row == null){
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            newsHolder = new NewsHolder(row);
            row.setTag(newsHolder);
        }
        else
        {
            newsHolder = (NewsHolder) row.getTag();
        }
        newsHolder.populateFrom(model.get(position));

        newsHolder.getBtn_add().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        newsHolder.getBtn_share().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickShare(position);
            }
        });

        return row;
    }

    private void onClickShare(int position) {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(model.get(position).getLink()))
                .build();

        ShareApi.share(content,null);
    }
}
