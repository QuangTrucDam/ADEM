package com.adem.ListPostMain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adem.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DELL on 3/7/2017.
 */

public class NewsHolder {
    private TextView txvTime, txvTitle, txvContent;
    private ImageView imgHinh;
    private Button btn_add,btn_share;

    public NewsHolder(View row){
        txvTime = (TextView) row.findViewById(R.id.txvTime);
        txvTitle = (TextView) row.findViewById(R.id.txvTitle);
        imgHinh = (ImageView) row.findViewById(R.id.imgHinh);
        txvContent = (TextView) row.findViewById(R.id.txvContent);
        btn_add = (Button)row.findViewById(R.id.buttom_new_add);
        btn_share = (Button)row.findViewById(R.id.buttom_new_share);
    }

    public Button getBtn_add() {
        return btn_add;
    }

    public Button getBtn_share() {
        return btn_share;
    }

    public void populateFrom(News news){
        txvTime.setText(news.getTime());
        txvTitle.setText(news.getTitle());
        imgHinh.setImageResource(R.drawable.no_image);

        ImageTask task = new ImageTask();
        task.execute(news.getImage());

        txvContent.setText(news.getContent());
    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            imgHinh.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bitmap = null;

            try {
                //Log.i("Image", params[0]);
                URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                //

            } catch (MalformedURLException e) {
                Log.e("Loi", e.toString());
            } catch (IOException e) {
                Log.e("Loi", e.toString());
            }


            return bitmap;
        }
    }
}
