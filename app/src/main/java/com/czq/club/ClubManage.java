package com.czq.club;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClubManage extends Activity {
    private List<club_manager_member_items> memberlist=new ArrayList<>();
    private List<ClubManagerActivityItems> activityItems=new ArrayList<>();
    //创建推送
    private Button btn_take_photo, btn_pick_photo,btn_cancle,cancel_save;
    private LinearLayout layout;
    private ImageView picture;
    private EditText et1,et2,et3,et4;
    public static final int TAKE_PHOTO=1;
    public static final int CHOOSE_PHOTO=2;
    private Uri imageUri;
    private ChangeType changeType;
    //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_manage);

        //头部三个按钮和按钮下面的线
        final Button people = (Button) findViewById(R.id.button_people);
        final Button task = (Button) findViewById(R.id.button_activity);
        final Button passage = (Button) findViewById(R.id.button_passage);
        final TextView linepeople = (TextView) findViewById(R.id.line_people);
        final TextView linetask = (TextView) findViewById(R.id.line_task);
        final TextView linepassage = (TextView) findViewById(R.id.line_passage);


        final LinearLayout linearpeople = (LinearLayout) findViewById(R.id.club_manager_member);

        final LinearLayout lineartask = (LinearLayout) findViewById(R.id.clubmanage_activity);
        final RelativeLayout relativeLayoutpassage = (RelativeLayout) findViewById(R.id.club_manager_passage);

        final ListView memberlistview = (ListView) findViewById(R.id.listview_member);

        final ListView activitylistview=(ListView)findViewById(R.id.listview_activity);
        final Button createactivity=(Button)findViewById(R.id.button_create_activity);

        //点击人员按钮的点击事件
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people.setTextColor(0xff0AAD32);
                task.setTextColor(0xff000000);
                passage.setTextColor(0xff000000);
                linepeople.setVisibility(View.VISIBLE);
                linepeople.setBackgroundColor(0xff0AAD32);
                linepassage.setVisibility(View.GONE);
                linetask.setVisibility(View.GONE);

                lineartask.setVisibility(View.GONE);
                relativeLayoutpassage.setVisibility(View.GONE);
                linearpeople.setVisibility(View.VISIBLE);

            }
        });

//点击任务按钮的点击事件
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setTextColor(0xff0AAD32);
                people.setTextColor(0xff000000);
                passage.setTextColor(0xff000000);
                linetask.setVisibility(View.VISIBLE);

                linetask.setBackgroundColor(0xff0AAD32);
                linepassage.setVisibility(View.GONE);
                linepeople.setVisibility(View.GONE);

                lineartask.setVisibility(View.VISIBLE);
                relativeLayoutpassage.setVisibility(View.GONE);
                linearpeople.setVisibility(View.GONE);

            }
        });

        //点击推送按钮的点击事件
        passage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passage.setTextColor(0xff0AAD32);
                task.setTextColor(0xff000000);
                people.setTextColor(0xff000000);
                linepassage.setVisibility(View.VISIBLE);
                linepassage.setBackgroundColor(0xff0AAD32);
                linepeople.setVisibility(View.GONE);
                linetask.setVisibility(View.GONE);

                lineartask.setVisibility(View.GONE);
                relativeLayoutpassage.setVisibility(View.VISIBLE);
                linearpeople.setVisibility(View.GONE);
            }
        });

        initmenmer();
        club_manager_member_items_adapter member_items_adapter = new club_manager_member_items_adapter(ClubManage.this, R.layout.club_manager_menber_item, memberlist);
        memberlistview.setAdapter(member_items_adapter);
//活动List view
        initactivity();
        ClubManagerActivityItemAdapter activityItemAdapter=new ClubManagerActivityItemAdapter(ClubManage.this,R.layout.club_manager_task_item,activityItems);
        activitylistview.setAdapter(activityItemAdapter);

        createactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ClubManage.this, ClubManagerCreateActivity.class);
                startActivity(intent1);
            }
    });


        //创建推送
        btn_take_photo = (Button) this.findViewById(R.id.take_photo);
        btn_pick_photo = (Button) this.findViewById(R.id.choose_from_album);
        btn_cancle=(Button)this.findViewById(R.id.cancle);
        picture=(ImageView) this.findViewById(R.id.picture);
        cancel_save=(Button)this.findViewById(R.id.cancel_save);
        et1=(EditText)this.findViewById(R.id.et1);et2=(EditText)this.findViewById(R.id.et2);et3=(EditText)this.findViewById(R.id.et3);et4=(EditText)this.findViewById(R.id.et4);

        layout=(LinearLayout)findViewById(R.id.pop_layout);

        picture.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                layout.setVisibility(View.VISIBLE);
                cancel_save.setVisibility(View.GONE);
            }
        });
        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
        layout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //添加按钮监听
        //拍照监听
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建File对象用于存储拍照后的照片
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch(IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(ClubManage.this,"com.example.cameraalbumtest.fileprovider",outputImage);
                }else{
                    imageUri= Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });

        //相册选择监听
        btn_pick_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(ClubManage.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ClubManage.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            }
        });

        //取消监听
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                cancel_save.setVisibility(View.VISIBLE);
            }
        });

        //发布监听
        cancel_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String club_name=et1.getText().toString();
                String theme=et2.getText().toString();
                String time=et3.getText().toString();
                String url=et4.getText().toString();
                Log.d("SelectPicPopupWindow:",club_name+"#"+theme+"#"+time+"#"+url);

//                MyThread thread=new MyThread();
//                new Thread(thread).start();
            }
        });

    }

    private void initactivity(){
        ClubManagerActivityItems item1=new ClubManagerActivityItems("活动1","未审核");
        activityItems.add(item1);
    }

    private void initmenmer(){
        club_manager_member_items item1=new club_manager_member_items(R.drawable.club4,"陈章琦","31701001","计算分院","1235855");
        memberlist.add(item1);
        club_manager_member_items item2=new club_manager_member_items(R.drawable.club4,"陈章琦","31701001","计算分院","1235855");
        memberlist.add(item2);
        club_manager_member_items item3=new club_manager_member_items(R.drawable.club4,"陈章琦","31701001","计算分院","1235855");
        memberlist.add(item3);
    }



    //创建推送
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        //将拍照照片显示出来
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setBackgroundColor(android.graphics.Color.parseColor("#00ffffff"));
                        picture.setImageBitmap(bitmap);
                        layout.setVisibility(View.GONE);
                        cancel_save.setVisibility(View.VISIBLE);
                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19)
                        handleImageOnKitKat(data);
                    else
                        handleImageBeforeKitKat(data);
                }
                break;
            default:
                break;
        }
    }

    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);    //打开相册
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documnets".equals(uri.getAuthority())){
                String id=docId.split(":")[1];
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            picture.setBackgroundColor(android.graphics.Color.parseColor("#00ffffff"));
            picture.setImageBitmap(bitmap);
            layout.setVisibility(View.GONE);
            cancel_save.setVisibility(View.VISIBLE);
        }else
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
    }
//

}
