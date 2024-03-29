package com.inotec.inoarte;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mImageUrls;
    private Map<String, Bitmap> mBitmapCache;

    public ImageAdapter(Context context, String[] imageUrls) {
        mContext = context;
        mImageUrls = imageUrls;
        mBitmapCache = new HashMap<>();
    }

    @Override
    public int getCount() {
        return mImageUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        String imageUrl = mImageUrls[position];
        if (mBitmapCache.containsKey(imageUrl)) {
            imageView.setImageBitmap(mBitmapCache.get(imageUrl));
        } else {
            new ImageLoadTask(imageView, position).execute(imageUrl);
        }

        return imageView;
    }

    private class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView mImageView;
        private int mPosition;

        public ImageLoadTask(ImageView imageView, int position) {
            mImageView = imageView;
            mPosition = position;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String imageUrl = params[0];
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                mBitmapCache.put(mImageUrls[mPosition], result);
                mImageView.setImageBitmap(result);
            }
        }
    }
}