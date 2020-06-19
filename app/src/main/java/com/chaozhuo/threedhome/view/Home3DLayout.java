package com.chaozhuo.threedhome.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chaozhuo.threedhome.R;
import com.chaozhuo.threedhome.TargetActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fewwind on 18-8-27.
 */

public class Home3DLayout extends FrameLayout {

    private ThreeDView view1;
    private ThreeDView view2;
    private ThreeDView view3;
    private ThreeDView view4;
    private ThreeDView view5;
    private ThreeDView view6;
    private ThreeDView view7;
    private ThreeDView view8;
    private ThreeDView viewMid;
    List<ThreeDView> mList = new ArrayList<>();
    ShadowView viewLeft;
    ShadowView viewRight;
    LinearLayout layout2;
    LinearLayout layout4;
    LinearLayout layoutGroup;
    int widthPadding;
    int hPadding;

    ReflectView mReflectMid;

    public Home3DLayout(@NonNull Context context) {
        this(context, null);
    }

    public Home3DLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Home3DLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        widthPadding = getResources().getDimensionPixelSize(R.dimen.threed_padding_width);
        hPadding = getResources().getDimensionPixelSize(R.dimen.threed_padding_height);
        LayoutInflater.from(context).inflate(R.layout.view_3d_layout, this);
        view1 = findViewById(R.id.three_view1);
        view2 = findViewById(R.id.three_view2);
        view3 = findViewById(R.id.three_view3);
        view4 = findViewById(R.id.three_view4);
        Logger.w("Grpup = 构造方法");
        view5 = findViewById(R.id.three_view5);
        view6 = findViewById(R.id.three_view6);
        view7 = findViewById(R.id.three_view7);
        view8 = findViewById(R.id.three_view8);
        viewMid = findViewById(R.id.three_view_mid);
        viewLeft = findViewById(R.id.shadow_left);
        viewRight = findViewById(R.id.shadow_right);
        layout2 = findViewById(R.id.layout_2);
        layout4 = findViewById(R.id.layout_4);
        layoutGroup = findViewById(R.id.layout_group);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layout2.getLayoutParams();
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) layout4.getLayoutParams();
        LinearLayout.LayoutParams paramsMid = (LinearLayout.LayoutParams) viewMid.getLayoutParams();
        params2.bottomMargin = hPadding;
        params2.topMargin = hPadding;
        params4.bottomMargin = hPadding;
        params4.topMargin = hPadding;
        paramsMid.bottomMargin = hPadding * 2;
        paramsMid.topMargin = hPadding * 2;
        layout2.setLayoutParams(params2);
        layout4.setLayoutParams(params4);
        viewMid.setLayoutParams(paramsMid);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutGroup.getLayoutParams();
        params.height = getResources().getDisplayMetrics().heightPixels / 2;
        layoutGroup.setLayoutParams(params);

        float[] des1 = {0, 0, 0, 0, 0, 4, 0, hPadding};
        float[] des2 = {0, 4, 0, 0, 0, hPadding, 0, 0};
        float[] des3 = {0, 0, 0, 0, 0, 10, 0, hPadding};
        float[] des4 = {0, hPadding, 0, 0, 0, 8, 0, 12};
        float[] des5 = {0, 12, 0, 8, 0, hPadding, 0, 6};

        float[] des6 = {0, hPadding, 0, 0, 0, 0, 0, 0};
        float[] des7 = {0, 0, 0, hPadding, 0, 0, 0, 0};
        float[] des8 = {0, hPadding, 0, hPadding, 0, 0, 0, 0};
        float[] desMid = {0, 0, 0, 0, 0, 0, 0, 0};
        view1.setDst(des1);
        view2.setDst(des2);
        view3.setDst(des3);
        view4.setDst(des4);
        view5.setDst(des5);
        view6.setDst(des6);
        view7.setDst(des7);
        view8.setDst(des8);
        view8.setLine(ThreeDView.Line.Right);
        viewMid.setDst(desMid);
        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        mList.add(view4);
        mList.add(view5);
        mList.add(view6);
        mList.add(view7);
        mList.add(view8);
        mList.add(viewMid);
        float[] left = {2f / 10, 8.8f / 10, 1, 8.5f / 10, 1, 1};
        float[] right = {0, 8.5f / 10, 8f / 10, 8.8f / 10, 0, 1};
        viewLeft.setPathPos(left);
        viewRight.setPathPos(right);

        view1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), TargetActivity.class), ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(), view1, "fewwind").toBundle());
            }
        });
        viewMid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), TargetActivity.class), ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(), viewMid, "fewwind").toBundle());
            }
        });
        mReflectMid = findViewById(R.id.reflect_mid);
        mReflectMid.setDst(desMid);
        for (ThreeDView view : mList) {

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Logger.e("Grpup = onMeasure");

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Logger.w("Grpup = onLayout");
    }

}
