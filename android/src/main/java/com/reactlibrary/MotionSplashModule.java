package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class MotionSplashModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public MotionSplashModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "MotionSplash";
    }

    @ReactMethod
    public void animate(String animationType) {
        startAnimation(animationType);
    }

    private void startAnimation(String animationType) {
        MotionSplashAnimationType type;

        if (animationType != null) {
            switch (animationType) {
                case "RotateOut":
                    type = MotionSplashAnimationType.rotateOut;
                    break;
                case "PopAndZoomOut":
                    type = MotionSplashAnimationType.popAndZoomOut;
                    break;
                case "SqueezeAndZoomOut":
                    type = MotionSplashAnimationType.squeezeAndZoomOut;
                    break;
                case "SwingAndZoomOut":
                    type = MotionSplashAnimationType.swingAndZoomOut;
                    break;
                case "WobbleAndZoomOut":
                    type = MotionSplashAnimationType.wobbleAndZoomOut;
                    break;
                case "HeartBeat":
                    type = MotionSplashAnimationType.heartBeat;
                    break;
                default:
                    type = MotionSplashAnimationType.twitter;
            }
        } else {
            type = MotionSplashAnimationType.twitter;
        }

        MotionSplash.animate(type);
    }

    @ReactMethod
    public void useCustomColor(boolean isApplyForIconImage, int color) {
        MotionSplash.useCustomColor(isApplyForIconImage, color);
    }

    @ReactMethod
    public void setMinimumBeats(int minimumBeats) {
        MotionSplash.setMinimumBeats(minimumBeats);
    }

    @ReactMethod
    public void hide() {
        MotionSplash.hide();
    }
}
