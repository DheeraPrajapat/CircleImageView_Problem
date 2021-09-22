package com.example.circleimageviewproblem;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int READ_EXTERNAL_REQUEST=1999;
    private CircleImageView imageView;
    RecyclerView recyclerView;
    ArrayList<ImageData> arrayList;
    ImageAdapter imageAdapter;
    Button button,button2,button3;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int MY_STORAGE_PERMISSION_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        button.setOnClickListener(v->{
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        button3.setOnClickListener(v->{
            recyclerView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
        });
    }

    private void initViews()
    {
        toastSetup();
        imageView=findViewById(R.id.circleImageView);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        recyclerView=findViewById(R.id.recyclerView);
        setUpImageArrayList();
        imageAdapter=new ImageAdapter(this,arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    private void setUpImageArrayList(){
        arrayList= new ArrayList<>();
        arrayList.add(new ImageData("Emoji1","https://www.macmillandictionaryblog.com/wp-content/uploads/2017/07/emoji-810x514.jpg"));
        arrayList.add(new ImageData("Emoji2","https://www.google.com/search?q=emoji+images&client=safari&rls=en&tbm=isch&source=iu&ictx=1&fir=iSprYLrFf6cmKM%252CAO0267HOwvJEOM%252C_&vet=1&usg=AI4_-kScw6OQHdoa52OXWqZnAAec_pSR1Q&sa=X&ved=2ahUKEwj0suDks5LzAhXIAnIKHcMBBREQ9QF6BAgOEAE#imgrc=iSprYLrFf6cmKM"));
        arrayList.add(new ImageData("Emoji3","https://www.google.com/search?q=emoji+images&client=safari&rls=en&tbm=isch&source=iu&ictx=1&fir=wUo1ErjOLBRhgM%252C-T1AkjcrovItdM%252C_&vet=1&usg=AI4_-kS_rTo2Z7ss5FW26wolTawBRIrjMA&sa=X&ved=2ahUKEwj0suDks5LzAhXIAnIKHcMBBREQ9QF6BAgDEAE#imgrc=wUo1ErjOLBRhgM"));
        arrayList.add(new ImageData("Emoji4","https://www.google.com/search?q=emoji+images&client=safari&rls=en&tbm=isch&source=iu&ictx=1&fir=b5X4AHM5W4a-KM%252CAU8EIhXuVBlFmM%252C_&vet=1&usg=AI4_-kQn6IHVchoTAqUZDztxEX1TRPlkvw&sa=X&ved=2ahUKEwj0suDks5LzAhXIAnIKHcMBBREQ9QF6BAgIEAE#imgrc=b5X4AHM5W4a-KM"));
        arrayList.add(new ImageData("Emoji5","https://www.google.com/search?q=emoji+images&client=safari&rls=en&tbm=isch&source=iu&ictx=1&fir=LztHKeoWTd3O2M%252CprYxgwHVHdaKNM%252C_&vet=1&usg=AI4_-kRvOSBHiG8nFcTp0vcnutH6yaE9pg&sa=X&ved=2ahUKEwj0suDks5LzAhXIAnIKHcMBBREQ9QF6BAgNEAE#imgrc=LztHKeoWTd3O2M"));
    }

    private void toastSetup(){
        View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.toast_layout,null,false);
        Toast toast=new Toast(this);
        toast.setView(view);
    }

}