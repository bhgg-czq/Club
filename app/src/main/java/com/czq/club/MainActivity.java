package com.czq.club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    private ViewPager pager = null;
    private AtomicInteger what = new AtomicInteger();
    private boolean isContinue = true;

    public  List<Club> clubLists = new ArrayList<>();
    private HorizontalListView listView = null;

    private List<Passage> passageList = new ArrayList<>();
    private NoScrollListview passlist = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        clubLists = initClub();
        listView = (HorizontalListView) findViewById(R.id.clublist);
        listView.setAdapter(new MyAdapter(clubLists,this.getBaseContext()));

        passageList = initPassage();
        passlist = findViewById(R.id.passagelist);
        initViewPager();
        passlist.setAdapter(new PassageAdapter(passageList,this.getBaseContext()));

        //底部的菜单栏的监听
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final SearchView searchView=(SearchView)findViewById(R.id.search);//设置搜索框的监听事件


    }
//底部菜单栏的点击事件
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                 //   viewpager_launch.setCurrentItem(0);
                    Intent intent1=new Intent(MainActivity.this,ClubActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                  //  viewpager_launch.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                   // viewpager_launch.setCurrentItem(2);
                    Intent intent=new Intent(MainActivity.this,MyActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };


    public List<Passage> initPassage(){
        List<Passage> list = new ArrayList<>();
        list.add(new Passage(clubLists.get(0),"6小时前","老师上课棒棒哒"));
        list.add(new Passage(clubLists.get(1),"6小时前","老师上课棒棒哒"));
        list.add(new Passage(clubLists.get(2),"6小时前","老师上课棒棒哒"));
        list.add(new Passage(clubLists.get(3),"6小时前","老师上课棒棒哒"));
        list.add(new Passage(clubLists.get(4),"6小时前","老师上课棒棒哒"));
        return list;
    }

    public List<Club> initClub(){
        List<Club> list = new ArrayList<Club>();
        list.add(new Club(R.drawable.club1,"club1"));
        list.add(new Club(R.drawable.club2,"club2"));
        list.add(new Club(R.drawable.club3,"club3"));
        list.add(new Club(R.drawable.club4,"club4"));
        list.add(new Club(R.drawable.club5,"club5"));
        list.add(new Club(R.drawable.club6,"club6"));
        return list;
    }
    public  void initViewPager() {
        ViewPager advPager = (ViewPager) findViewById(R.id.pager);
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);

//		这里存放的是四张广告背景
        List<View> advPics = new ArrayList<View>();

        ImageView img1 = new ImageView(this);
        img1.setBackgroundResource(R.drawable.default1);
        advPics.add(img1);

        ImageView img2 = new ImageView(this);
        img2.setBackgroundResource(R.drawable.default2);
        advPics.add(img2);

        ImageView img3 = new ImageView(this);
        img3.setBackgroundResource(R.drawable.default3);
        advPics.add(img3);

        ImageView img4 = new ImageView(this);
        img4.setBackgroundResource(R.drawable.default4);
        advPics.add(img4);

//		对imageviews进行填充
        imageViews = new ImageView[advPics.size()];
//小图标
        for (int i = 0; i < advPics.size(); i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(5, 5, 5, 5);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(R.drawable.banner_dian_focus);
            } else {
                imageViews[i]
                        .setBackgroundResource(R.drawable.banner_dian_blur);
            }
            group.addView(imageViews[i]);
        }

        advPager.setAdapter(new AdvAdapter(advPics));
        advPager.setOnPageChangeListener(new GuidePageChangeListener());
        advPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();
                    }
                }
            }

        }).start();
    }


    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > imageViews.length - 1) {
            what.getAndAdd(-4);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
    }

    private final Handler viewHandler = new Handler() {
//！！！不知道有啥用，但是报错就先注释了！！！
        // @Override
//        public void handleMessage(Message msg) {
//            pager.setCurrentItem(msg.what);
//            super.handleMessage(msg);
//        }

    };

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            what.getAndSet(arg0);
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.drawable.banner_dian_focus);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.drawable.banner_dian_blur);
                }
            }

        }

    }

    private final class AdvAdapter extends PagerAdapter {
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

    }

    public class MyAdapter extends BaseAdapter {

        private List<Club> list ;
        private Context context;


        public MyAdapter(List<Club> list, Context context) {
            this.list = list;
            this.context=context;
        }

        @Override
        public int getCount() {
            return list==null?0:list.size();
        }

        @Override
        public Club getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //加载布局为一个视图
            Club club = list.get(position);
            View view;
            Holder holder;
            if(convertView==null){
                view = LayoutInflater.from(context).inflate(R.layout.clublist_item,null);
                holder = new Holder();
                holder.imageView = view.findViewById(R.id.header);
                holder.textView = view.findViewById(R.id.name);
                view.setTag(holder);
            }
            else {
                view = convertView;
                holder = (Holder )view.getTag();
            }

            holder.imageView.setImageResource(club.getImageId());
            holder.textView.setText(club.getName());

            return view;
        }

        public class Holder{
            ImageView imageView;
            TextView textView;
        }

    }


}
