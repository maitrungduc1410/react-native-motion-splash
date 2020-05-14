package com.reactlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Size;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;

@SuppressLint("ViewConstructor")
public class MotionSplashView extends View {
    public FrameLayout layout;
    public Drawable iconImage;
    public Drawable backgroundImage;
    public ImageView imageView;
    public MotionSplashAnimationType animationType;
    public double duration = 1500;
    public int minimumBeats = 1;
    public boolean heartAttack = false;

    public MotionSplashView(Context context, Drawable iconImage, Size iconInitialSize, @Nullable Drawable backgroundImage, @Nullable String backgroundColor) {
        super(context);
        this.imageView = new ImageView(context);
        this.iconImage = iconImage;
        this.imageView.setImageDrawable(iconImage);
        this.backgroundImage = backgroundImage;

        Resources r = getResources();
        float widthPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                iconInitialSize.getWidth(),
                r.getDisplayMetrics());
        float heightPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                iconInitialSize.getHeight(),
                r.getDisplayMetrics());

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) widthPx, (int) heightPx);
        layoutParams.gravity = Gravity.CENTER;
        this.imageView.setLayoutParams(layoutParams);
        this.animationType = MotionSplashAnimationType.twitter;

        layout = new FrameLayout(context);
        if (backgroundImage == null) {
            layout.setBackgroundColor(Color.parseColor(backgroundColor));
        } else {
            ImageView backgroundImageIV = new ImageView(context);
            backgroundImageIV.setImageDrawable(backgroundImage);
            backgroundImageIV.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            layout.addView(backgroundImageIV);
        }

        layout.addView(imageView);
    }

    public void startAnimation (@Nullable final MotionSplashViewListener mListener) {
        if (animationType == MotionSplashAnimationType.squeezeAndZoomOut) {
            playSqueezeAnimation(mListener);
        } else if (animationType == MotionSplashAnimationType.rotateOut) {
            playRotateOutAnimation(mListener);
        } else if (animationType == MotionSplashAnimationType.wobbleAndZoomOut) {
            playWoobleAnimation(mListener);
        } else if (animationType == MotionSplashAnimationType.swingAndZoomOut) {
            playSwingAnimation(mListener);
        } else if (animationType == MotionSplashAnimationType.popAndZoomOut) {
            playPopAnimation(mListener);
        } else if (animationType == MotionSplashAnimationType.heartBeat) {
            playHeartBeatAnimation(mListener);
        } else {
            playTwitterAnimation(mListener);
        }
    }

    public void playTwitterAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (this.imageView != null) {
            final SpringAnimation scaleX = new SpringAnimation(this.imageView, DynamicAnimation.SCALE_X, 0.75f);
            final SpringAnimation scaleY = new SpringAnimation(this.imageView, DynamicAnimation.SCALE_Y, 0.75f);

            scaleX.setStartVelocity(3);
            scaleX.getSpring().setDampingRatio(0.7f);
            scaleY.setStartVelocity(3);
            scaleY.getSpring().setDampingRatio(0.7f);

            scaleX.start();
            scaleY.start();

            scaleX.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if (mListener != null) {
                        mListener.onSplashEnd();
                    }

                    playZoomOutAnimation(mListener);
                }
            });
        }
    }

    public void playSqueezeAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (this.imageView != null) {
            final SpringAnimation scaleX = new SpringAnimation(this.imageView, DynamicAnimation.SCALE_X, 0.3f);
            final SpringAnimation scaleY = new SpringAnimation(this.imageView, DynamicAnimation.SCALE_Y, 0.3f);

            scaleX.setStartVelocity(10);
            scaleX.getSpring().setDampingRatio(2);
            scaleY.setStartVelocity(10);
            scaleY.getSpring().setDampingRatio(2);

            scaleX.start();
            scaleY.start();

            scaleX.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if (mListener != null) {
                        mListener.onSplashEnd();
                    }

                    playZoomOutAnimation(mListener);
                }
            });
        }
    }

    public void playRotateOutAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (this.imageView != null) {
            final SpringAnimation scaleX = new SpringAnimation(this.imageView, DynamicAnimation.SCALE_X, 20);
            final SpringAnimation scaleY = new SpringAnimation(this.imageView, DynamicAnimation.SCALE_Y, 20);
            final SpringAnimation rotation  = new SpringAnimation(this.imageView, DynamicAnimation.ROTATION, 90);
            final SpringAnimation alpha  = new SpringAnimation(this.imageView, DynamicAnimation.ALPHA, 0);

            scaleX.setStartVelocity(3);
            scaleX.getSpring().setDampingRatio(5f);
            scaleY.setStartVelocity(3);
            scaleY.getSpring().setDampingRatio(5f);
            rotation.setStartVelocity(3);
            rotation.getSpring().setDampingRatio(5f);
            alpha.getSpring().setDampingRatio(1.5f);

            scaleX.start();
            scaleY.start();
            rotation.start();
            alpha.start();

            alpha.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if (mListener != null) {
                        mListener.onSplashEnd();
                    }
                }
            });
        }
    }

    public void playWoobleAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (imageView != null) {
            float woobleForce = 0.5f;

            Keyframe rotation1 = Keyframe.ofFloat(0, 0);
            Keyframe rotation2 = Keyframe.ofFloat(0.2f, 0.3f * woobleForce * 90);
            Keyframe rotation3 = Keyframe.ofFloat(0.4f, -0.3f * woobleForce * 90);
            Keyframe rotation4 = Keyframe.ofFloat(0.6f, 0.3f * woobleForce * 90);
            Keyframe rotation5 = Keyframe.ofFloat(0.8f, 0);
            Keyframe rotation6 = Keyframe.ofFloat(1.0f, 0);

            Keyframe positionX1 = Keyframe.ofFloat(0, 0);
            Keyframe positionX2 = Keyframe.ofFloat(0.2f, 100 * woobleForce);
            Keyframe positionX3 = Keyframe.ofFloat(0.4f, -100 * woobleForce);
            Keyframe positionX4 = Keyframe.ofFloat(0.6f, 100 * woobleForce);
            Keyframe positionX5 = Keyframe.ofFloat(0.8f, 0);
            Keyframe positionX6 = Keyframe.ofFloat(1.0f, 0);

            PropertyValuesHolder rotation = PropertyValuesHolder.ofKeyframe("rotation", rotation1, rotation2, rotation3, rotation4, rotation5, rotation6);
            PropertyValuesHolder positionX = PropertyValuesHolder.ofKeyframe("translationX", positionX1, positionX2, positionX3, positionX4, positionX5, positionX6);
            ObjectAnimator animator =
                    ObjectAnimator.ofPropertyValuesHolder(imageView, rotation, positionX);
            animator.setDuration((long) (duration / 2));
            animator.setRepeatCount(1);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mListener != null) {
                        mListener.onSplashEnd();
                    }

                    playZoomOutAnimation(mListener);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    public void playSwingAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (imageView != null) {
            float swingForce = 0.8f;

            Keyframe rotation1 = Keyframe.ofFloat(0, 0);
            Keyframe rotation2 = Keyframe.ofFloat(0.2f, 0.3f * swingForce * 90);
            Keyframe rotation3 = Keyframe.ofFloat(0.4f, -0.3f * swingForce * 90);
            Keyframe rotation4 = Keyframe.ofFloat(0.6f, 0.3f * swingForce * 90);
            Keyframe rotation5 = Keyframe.ofFloat(0.8f, 0);
            Keyframe rotation6 = Keyframe.ofFloat(1.0f, 0);

            PropertyValuesHolder rotation = PropertyValuesHolder.ofKeyframe("rotation", rotation1, rotation2, rotation3, rotation4, rotation5, rotation6);
            ObjectAnimator animator =
                    ObjectAnimator.ofPropertyValuesHolder(imageView, rotation);
            animator.setDuration((long) (duration / 2));
            animator.setRepeatCount(1);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (mListener != null) {
                        mListener.onSplashStart();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mListener != null) {
                        mListener.onSplashEnd();
                    }

                    playZoomOutAnimation(mListener);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    public void playPopAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (imageView != null) {
            float popScale = 1f;

            Keyframe scale1 = Keyframe.ofFloat(0, 1 * popScale);
            Keyframe scale2 = Keyframe.ofFloat(0.2f, 1.2f * popScale);
            Keyframe scale3 = Keyframe.ofFloat(0.4f, 0.7f * popScale);
            Keyframe scale4 = Keyframe.ofFloat(0.6f, 1.2f * popScale);
            Keyframe scale5 = Keyframe.ofFloat(0.8f, 1 * popScale);
            Keyframe scale6 = Keyframe.ofFloat(1.0f, 1 * popScale);

            PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX", scale1, scale2, scale3, scale4, scale5, scale6);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY", scale1, scale2, scale3, scale4, scale5, scale6);

            ObjectAnimator animator =
                    ObjectAnimator.ofPropertyValuesHolder(imageView, scaleX, scaleY);
            animator.setDuration((long) (duration / 2));
            animator.setRepeatCount(1);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (mListener != null) {
                        mListener.onSplashStart();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mListener != null) {
                        mListener.onSplashEnd();
                    }

                    playZoomOutAnimation(mListener);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    public void playHeartBeatAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (imageView != null) {
            float popScale = 1f;

            Keyframe scale1 = Keyframe.ofFloat(0, 1 * popScale);
            Keyframe scale2 = Keyframe.ofFloat(0.25f, 1.1f * popScale);
            Keyframe scale3 = Keyframe.ofFloat(0.35f, 1.2f * popScale);
            Keyframe scale4 = Keyframe.ofFloat(0.55f, 1.35f * popScale);
            Keyframe scale5 = Keyframe.ofFloat(1.0f, 1 * popScale);

            PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX", scale1, scale2, scale3, scale4, scale5);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY", scale1, scale2, scale3, scale4, scale5);

            ObjectAnimator animator =
                    ObjectAnimator.ofPropertyValuesHolder(imageView, scaleX, scaleY);
            animator.setDuration((long) (duration / 2));
            animator.setRepeatCount(minimumBeats > 0 ? minimumBeats : 1);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (mListener != null) {
                        mListener.onSplashStart();
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!heartAttack) {
                        playHeartBeatAnimation(mListener);
                    } else {
                        if (mListener != null) {
                            mListener.onSplashEnd();
                        }

                        playZoomOutAnimation(mListener);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    public void playZoomOutAnimation(@Nullable final MotionSplashViewListener mListener) {
        if (imageView != null) {
            double growDuration = duration * 0.3;

            imageView.animate()
                    .scaleX(20)
                    .scaleY(20)
                    .alpha(0)
                    .setDuration((long) growDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (mListener != null) {
                                mListener.onSplashEnd();
                            }
                        }
                    });

        }
    }
}
