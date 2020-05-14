package com.reactlibrary;

import android.widget.ImageView;

public interface MotionSplashAnimatable {
    ImageView getImageView();
    void setImageView(ImageView imageView);

    /// The animation type
    MotionSplashAnimationType getAnimationType();
    void setAnimationType(MotionSplashAnimationType animationType);

    /// The duration of the overall animation
    Double getDuration();
    void setDuration(Double duration);

    /// The delay to play the animation
    Double getDelay();
    void setDelay(Double delay);

    /// The trigger to stop heartBeat animation
    boolean getHeartAttack();
    void setHeartAttack(boolean heartAttack);

    /// The minimum number of beats before removing the splash view
    int getMinimumBeats();
    void setMinimumBeats(int minimumBeats);
}
