package com.example.hoang.lesson3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycelerView;
    private RecycelerViewAdapter mAdapter;
    private List<File> mListGallery;
    private static final String PATH = Environment.getExternalStorageDirectory().getPath();
    private static final String PATH_CAMERA = PATH + "/DCIM/Camera";
    private static final String TAG = "CHECK_LOAD_IMAGE";
    private static final String MESSAGE_PERMISSION_GRANTED = "Permission Granted";
    private static final String MESSAGE_PERMISSION_REVOKED = "Permission Revoked";
    private static final int SPAN_COUNT = 2;
    private static final int REQUEST_READ_STORAGE_PERMISSION_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addevent();
    }

    private void addevent() {
        addRecycelerView();
    }

    private void init() {
        mRecycelerView = findViewById(R.id.recyclerview);
    }

    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, MESSAGE_PERMISSION_GRANTED);
                return true;
            } else {
                Log.v(TAG, MESSAGE_PERMISSION_REVOKED);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE_PERMISSION_CODE);
                return false;
            }
        } else {
            Log.v(TAG, MESSAGE_PERMISSION_GRANTED);
            return true;
        }
    }

    private void getListImages() {
        if (isReadStoragePermissionGranted()) {
            File[] images = getAllImageFromLocal();
            for (File i : images) {
                mListGallery.add(i);
                Log.d(TAG, i.toString());
            }
        }
    }

    private File[] getAllImageFromLocal() {
        File file = new File(PATH_CAMERA);
        File[] images = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getAbsolutePath().endsWith(".png")
                        || file.getAbsolutePath().endsWith(".jpg")
                        || file.getAbsolutePath().endsWith("jpeg");
            }
        });
        return images;
    }

    private void addRecycelerView() {
        getListImages();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), SPAN_COUNT);
        mRecycelerView.setLayoutManager(layoutManager);
        mAdapter = new RecycelerViewAdapter(mListGallery, this);
        mRecycelerView.setAdapter(mAdapter);
    }
}


