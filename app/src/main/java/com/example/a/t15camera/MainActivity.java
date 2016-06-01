package com.example.a.t15camera;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final int PHOTO_COMPLETED = 100;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        //Test

    }

    public void onBtnClick(View view) {
        String path = Environment.getExternalStorageDirectory() + "/t01.jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
        startActivityForResult(intent, PHOTO_COMPLETED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_COMPLETED) {
            if (resultCode == RESULT_OK) {
                String path = Environment.getExternalStorageDirectory() + "/t01.jpg";
                Bitmap bitmap;
                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inSampleSize = 4;

                bitmap = BitmapFactory.decodeFile(path, option);
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
