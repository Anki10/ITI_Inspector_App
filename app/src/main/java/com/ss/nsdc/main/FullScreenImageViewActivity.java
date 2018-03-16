package com.ss.nsdc.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ss.nsdc.R;
import com.ss.nsdc.utility.Utility;

public class FullScreenImageViewActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_view);

        imageView = (ImageView) findViewById(R.id.full_screen_container);
        String imgUrl = null;

        if (getIntent().getStringExtra("imgUrl") != null) {
            imgUrl = getIntent().getStringExtra("imgUrl");
            Picasso.with(FullScreenImageViewActivity.this).load(Utility.getTempFile(this, imgUrl))
                    .skipMemoryCache()
                    .fit()
                    .placeholder(R.drawable.load_icon)
                    .into(imageView);

        } else if (getIntent().getStringExtra("imgServerUrl") != null) {
            imgUrl = getIntent().getStringExtra("imgServerUrl");
            Picasso.with(FullScreenImageViewActivity.this).load(imgUrl)
                    .skipMemoryCache()
                    .fit()
                    .placeholder(R.drawable.load_icon)
                    .into(imageView);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_screen_image_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
