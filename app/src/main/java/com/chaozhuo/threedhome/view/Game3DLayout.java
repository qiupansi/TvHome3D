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

/**
 * Created by fewwind on 18-8-27.
 */

public class Game3DLayout extends FrameLayout {

    private ThreeDView view1;
    private ThreeDView view2;
    private ThreeDView view3;
    private ThreeDView view4;
    private ThreeDView view5;
    private ThreeDView view6;
    private ThreeDView view7;
    private ThreeDView view8;
    private ThreeDView view9;
    private ThreeDView view10;
    private ThreeDView view11;
    private ThreeDView view12;
    ShadowView viewLeft;
    ShadowView viewRight;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layoutGroup;
    int widthPadding;
    int hPadding;

    public Game3DLayout(@NonNull Context context) {
        this(context, null);
    }

    public Game3DLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Game3DLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        widthPadding = getResources().getDimensionPixelSize(R.dimen.threed_padding_width);
        hPadding = getResources().getDimensionPixelSize(R.dimen.threed_padding_height);
        LayoutInflater.from(context).inflate(R.layout.view_game_3d_layout, this);
        view1 = findViewById(R.id.game_view1);
        view2 = findViewById(R.id.game_view2);
        view3 = findViewById(R.id.game_view3);
        view4 = findViewById(R.id.game_view4);
        view5 = findViewById(R.id.game_view5);
        view6 = findViewById(R.id.game_view6);
        view7 = findViewById(R.id.game_view7);
        view8 = findViewById(R.id.game_view8);
        view9 = findViewById(R.id.game_view9);
        view10 = findViewById(R.id.game_view10);
        view11 = findViewById(R.id.game_view11);
        view12 = findViewById(R.id.game_view12);
        viewLeft = findViewById(R.id.shadow_left);
        viewRight = findViewById(R.id.shadow_right);

        layout2 = findViewById(R.id.game_layout2);
        layout3 = findViewById(R.id.game_layout3);
        layout4 = findViewById(R.id.game_layout4);
        layout5 = findViewById(R.id.game_layout5);
        layoutGroup = findViewById(R.id.game_layout_group);
        setLayoutPadding(layout2, hPadding);
        setLayoutPadding(layout3, hPadding * 2);
        setLayoutPadding(layout4, hPadding * 2);
        setLayoutPadding(layout5, hPadding);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutGroup.getLayoutParams();
        params.height = (int) (getResources().getDisplayMetrics().heightPixels * (0.6f));
        layoutGroup.setLayoutParams(params);

        float[] des1 = {0, 0, 0, 0, 0, 0, 0, hPadding};
        float[] des2 = {0, 0, 0, 0, 0, hPadding, 0, 0};


        float[] des9 = {0, hPadding, 0, 0, 0, 0, 0, 0};
        float[] des10 = {0, 0, 0, hPadding, 0, 0, 0, 0};
        float[] desMid = {0, 0, 0, 0, 0, 0, 0, 0};
        view1.setDst(des1);
        view2.setDst(des2);
        view3.setDst(des1);
        view4.setDst(des2);
        view5.setDst(desMid);
        view6.setDst(desMid);
        view7.setDst(desMid);
        view8.setDst(desMid);
        view9.setDst(des9);
        view10.setDst(des10);
        view11.setDst(des9);
        view12.setDst(des10);

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

    }

    private void setLayoutPadding(LinearLayout layout, int padding) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        params.bottomMargin = padding;
        params.topMargin = padding;
        layout.setLayoutParams(params);
    }
}
