package com.reactlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Size;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import com.facebook.react.bridge.UiThreadUtil;

public class MotionSplash {
    @SuppressLint("StaticFieldLeak")
    private static MotionSplashView revealingSplashView;

    public static void init(@NonNull final Activity activity, int bgSplashId, int iconImageId, Size iconInitialSize, String backgroundColor) {
        Context context = activity.getApplicationContext();
        Resources res = context.getApplicationContext().getResources();
        LayerDrawable layerDrawable = (LayerDrawable) ResourcesCompat.getDrawable(res, bgSplashId, null);
        BitmapDrawable iconImage = (BitmapDrawable) layerDrawable.findDrawableByLayerId(iconImageId);
        revealingSplashView = new MotionSplashView(context, iconImage, iconInitialSize, null, backgroundColor);
        FrameLayout.LayoutParams paramsActivity = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        activity.addContentView(revealingSplashView.layout, paramsActivity);
    }

    public static void init(@NonNull final Activity activity, int bgSplashId, int iconImageId, Size iconInitialSize, int bgImageId) {
        Context context = activity.getApplicationContext();
        Resources res = context.getApplicationContext().getResources();
        LayerDrawable layerDrawable = (LayerDrawable) ResourcesCompat.getDrawable(res, bgSplashId, null);
        BitmapDrawable iconImage = (BitmapDrawable) layerDrawable.findDrawableByLayerId(iconImageId);
        BitmapDrawable backgroundImage = (BitmapDrawable) layerDrawable.findDrawableByLayerId(bgImageId);
        revealingSplashView = new MotionSplashView(context, iconImage, iconInitialSize, backgroundImage, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        activity.addContentView(revealingSplashView.layout, params);
    }

    static void animate(@NonNull MotionSplashAnimationType animationType) {
        revealingSplashView.animationType = animationType;
        final ViewGroup parentLayout = (ViewGroup) revealingSplashView.layout.getParent();
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
                        revealingSplashView.layout
                                .animate()
                                .setDuration(450)
                                .alpha(0.0f)
                                .setInterpolator(new AccelerateInterpolator())
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        if (parentLayout != null) {
                                            parentLayout.removeView(revealingSplashView.layout);
                                        }
                                    }
                                }).start();
                    }
                });
            }
        });
    }

    static void useCustomColor(boolean isApplyForIconImage, int color) {
        if (isApplyForIconImage) {
            revealingSplashView.iconImage.setTint(color);
        } else {
            revealingSplashView.backgroundImage.setTint(color);
        }
    }

    static void setMinimumBeats(int minimumBeats) {
        revealingSplashView.minimumBeats = minimumBeats > 0 ? minimumBeats : 1;
    }

    static void hide() {
        revealingSplashView.heartAttack = true;
    }
}
