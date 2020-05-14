package com.reactlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.facebook.react.bridge.UiThreadUtil;

public class MotionSplash {
    private static MotionSplashView revealingSplashView;
    private static LinearLayout linearLayout;
    //    private static int bgSplashId;
    private static int iconImageId;
    private static int bgImageId;

    // TODO: background image is animated with iconImage
    public static void init(@NonNull final Activity activity, int bgSplashId, int iconImageId, int bgImageId, String backgroundColor) {
//        RevealSplash.bgSplashId = bgSplashId;
        MotionSplash.iconImageId = iconImageId;
        MotionSplash.bgImageId = bgImageId;

        Context context = activity.getApplicationContext();

        Resources res = context.getApplicationContext().getResources();
        Drawable iconImage = ResourcesCompat.getDrawable(res, bgSplashId, null);

        revealingSplashView = new MotionSplashView(context, iconImage);
        linearLayout = new LinearLayout(context);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.parseColor(backgroundColor));

        linearLayout.addView(revealingSplashView.imageView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        activity.addContentView(linearLayout,params);
    }

    public static void animate(@NonNull MotionSplashAnimationType animationType) {
        revealingSplashView.animationType = animationType;
        final ViewGroup parentLayout = (ViewGroup) linearLayout.getParent();

        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                revealingSplashView.startAnimation(new MotionSplashViewListener() {
                    @Override
                    public void onSplashStart() {
                    }

                    @Override
                    public void onSplashUpdate() {

                    }

                    @Override
                    public void onSplashEnd() {
                        linearLayout
                                .animate()
                                .setDuration(450)
                                .alpha(0.0f)
                                .setInterpolator(new AccelerateInterpolator())
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        if (parentLayout != null) {
                                            parentLayout.removeView(linearLayout);
                                        }
                                    }
                                }).start();
                    }
                });
            }
        });
    }

    public static void useCustomColor(boolean isApplyForIconImage, int color) {
        LayerDrawable layerDrawable = (LayerDrawable) revealingSplashView.iconImage;

        if (isApplyForIconImage) {
            BitmapDrawable iconImageDrawable = (BitmapDrawable) layerDrawable.findDrawableByLayerId(iconImageId);
            if (iconImageDrawable != null) {
                iconImageDrawable.setTint(color);
            }
        } else {
            BitmapDrawable bgImmageDrawable = (BitmapDrawable) layerDrawable.findDrawableByLayerId(bgImageId);
            if (bgImmageDrawable != null) {
                bgImmageDrawable.setTint(color);
            }
        }
    }

    public static void setMinimumBeats(int minimumBeats) {
        revealingSplashView.minimumBeats = minimumBeats > 0 ? minimumBeats : 1;
    }

    public static void hide() {
        revealingSplashView.heartAttack = true;
    }
}
