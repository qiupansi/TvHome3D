package com.chaozhuo.threedhome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chaozhuo.threedhome.base.QuickPageAdapter;
import com.chaozhuo.threedhome.base.ViewPager;
import com.chaozhuo.threedhome.util.transformer.RotateYTransformer;
import com.chaozhuo.threedhome.util.transformer.UltraDepthScaleTransformer;
import com.chaozhuo.threedhome.view.Game3DLayout;
import com.chaozhuo.threedhome.view.Home3DLayout;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

//更多TV项目资源(如桌面，直播点播，教育，应用市场，文件管理器，设置，酒店应用等)，添加微信：qiupansi
//If you want more TV project resources,such as TvLauncher,TvLive,TvAppStore,TvSettings,TvFileManager,TvEducation,TvHotel,TvMusic,TvRemote and so on，Add me wechat：qiupansi
public class ThreeDActivity extends AppCompatActivity {

    ViewPager mViewPager;
    List<View> mDatas = new ArrayList<>();
    GifImageView mGifView;
    GifDrawable gifDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3d);
        Intent intent = new Intent();
        intent.setAction("com.orbbec.TouchCursorExample.CursorService");
        intent.setPackage(getPackageName());
        startService(intent);

        mViewPager = findViewById(R.id.id_pager);
        mGifView = findViewById(R.id.gif_view);
        gifDrawable = (GifDrawable) mGifView.getDrawable();
        initData();
        handGifAnim();
//        mHandler.postDelayed(showAnim,5000);
//        Intent intent = new Intent(this, GestureService.class);
//        intent.setAction(getPackageName());
//        startService(intent);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private void handGifAnim() {
//        gifDrawable.setLoopCount(3);
        gifDrawable.addAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationCompleted(int loopNumber) {
                if (loopNumber == 0) {
                    gifDrawable.stop();
                    mHandler.postDelayed(showAnim, 5000);
                }
            }
        });
    }

    Runnable showAnim = new Runnable() {
        @Override
        public void run() {
            gifDrawable.start();
//            gifDrawable.setLoopCount(3);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    private void initData() {
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                mDatas.add(new Home3DLayout(this));
            } else {
                mDatas.add(new Game3DLayout(this));
            }
        }
        mViewPager.setAdapter(new QuickPageAdapter<View>(mDatas));
//        mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
//        mViewPager.setPageTransformer(true, new CubePageTransformer());
        mViewPager.setPageTransformer(true, new RotateYTransformer());
        mViewPager.setPageTransformer(true, new UltraDepthScaleTransformer());
//        mViewPager.setPageTransformer(true, new CubeInRotationTransformation());
        mDatas.get(0).post(new Runnable() {
            @Override
            public void run() {
                Logger.e("post 方法");
            }
        });
    }

}
