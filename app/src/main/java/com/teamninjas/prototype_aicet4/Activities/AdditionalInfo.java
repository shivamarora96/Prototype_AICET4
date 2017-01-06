package com.teamninjas.prototype_aicet4.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.teamninjas.prototype_aicet4.Utilities.Fn;
import com.teamninjas.prototype_aicet4.R;
import com.teamninjas.prototype_aicet4.Others.UserObject.college;
import com.teamninjas.prototype_aicet4.Adapters.collegeAdapter;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdditionalInfo extends AppCompatActivity {


    private List<college> collegeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private collegeAdapter mAdapter;
    private Bitmap image = null;

    private static int RESULT_LOAD_IMAGE = 1;
    private static int CAMERA_PIC_REQUEST= 0;
    ImageView prof;
  //  String colleges[]={"MAIT","BVP","HMR","USIT","NIEC","IIT","NSIT","DTU"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mAdapter = new collegeAdapter(collegeList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        preparecollegeData();

        prof=(ImageView)findViewById(R.id.profile_pic);
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder getImageFrom = new AlertDialog.Builder(AdditionalInfo.this);
                getImageFrom.setTitle("Select:");
                final CharSequence[] opsChars = {"Camera","Gallery"};
                //Toast.makeText(getContext(),"tapped",Toast.LENGTH_LONG).show();
                getImageFrom.setItems(opsChars, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                        } else if (which == 1) {
                            Intent i = new Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, RESULT_LOAD_IMAGE);
                        }
                        dialog.dismiss();
                    }
                });
                getImageFrom.create();
                getImageFrom.show();



              /*  Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);*/
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == this.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
//            Bitmap bmp = null;
            try {
                Uri reduceSizePath = Fn.getImageContentUri(this, Fn.decodeFile(picturePath,500,500));
                image = getBitmapFromUri(reduceSizePath);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            prof.setImageBitmap(image);
        } else if(requestCode==CAMERA_PIC_REQUEST&& resultCode == this.RESULT_OK && null != data){
            image = (Bitmap) data.getExtras().get("data");

            prof.setImageBitmap(image); //when camera is selected
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = this.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }




    private void preparecollegeData() {
        college c = new college("Maharaja Agrasen Institute of Technology");
        collegeList.add(c);

        c = new college("Bharti Vidyapeeth College of Engineering");
        collegeList.add(c);

        c = new college("HMR");
        collegeList.add(c);
        c = new college("MSIT");
        collegeList.add(c);
        c = new college("NIEC");
        collegeList.add(c);
        c = new college("IIT");
        collegeList.add(c);
        c = new college("NSIT");
        collegeList.add(c);
        c = new college("DTU");
        collegeList.add(c);
        c = new college("MAIT");
        collegeList.add(c);

        c = new college("BVP");
        collegeList.add(c);

        c = new college("HMR");
        collegeList.add(c);
        c = new college("MSIT");
        collegeList.add(c);
        c = new college("NIEC");
        collegeList.add(c);
        c = new college("IIT");
        collegeList.add(c);
        c = new college("NSIT");
        collegeList.add(c);
        c = new college("DTU");
        collegeList.add(c);
        c = new college("MAIT");
        collegeList.add(c);

        c = new college("BVP");
        collegeList.add(c);

        c = new college("HMR");
        collegeList.add(c);
        c = new college("MSIT");
        collegeList.add(c);
        c = new college("NIEC");
        collegeList.add(c);
        c = new college("IIT");
        collegeList.add(c);
        c = new college("NSIT");
        collegeList.add(c);
        c = new college("DTU");
        collegeList.add(c);
        c = new college("MAIT");
        collegeList.add(c);

        c = new college("BVP");
        collegeList.add(c);

        c = new college("HMR");
        collegeList.add(c);
        c = new college("MSIT");
        collegeList.add(c);
        c = new college("NIEC");
        collegeList.add(c);
        c = new college("IIT");
        collegeList.add(c);
        c = new college("NSIT");
        collegeList.add(c);
        c = new college("DTU");
        collegeList.add(c);
        c = new college("MAIT");
        collegeList.add(c);

        c = new college("BVP");
        collegeList.add(c);

        c = new college("HMR");
        collegeList.add(c);
        c = new college("MSIT");
        collegeList.add(c);
        c = new college("NIEC");
        collegeList.add(c);
        c = new college("IIT");
        collegeList.add(c);
        c = new college("NSIT");
        collegeList.add(c);
        c = new college("DTU");
        collegeList.add(c);
        c = new college("MAIT");
        collegeList.add(c);

        c = new college("BVP");
        collegeList.add(c);

        c = new college("HMR");
        collegeList.add(c);
        c = new college("MSIT");
        collegeList.add(c);
        c = new college("NIEC");
        collegeList.add(c);
        c = new college("IIT");
        collegeList.add(c);
        c = new college("NSIT");
        collegeList.add(c);
        c = new college("DTU");
        collegeList.add(c);

        mAdapter.notifyDataSetChanged();
    }

    public void finalsub(View view){
        Intent i =new Intent(AdditionalInfo.this,MasterAcitivty.class);
        startActivity(i);
    }
}
