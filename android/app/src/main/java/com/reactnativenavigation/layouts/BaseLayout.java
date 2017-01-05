package com.reactnativenavigation.layouts;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.reactnativenavigation.params.SlidingOverlayParams;
import com.reactnativenavigation.screens.Screen;
import com.reactnativenavigation.utils.ViewUtils;
import com.reactnativenavigation.views.ContentView;
import com.reactnativenavigation.views.slidingOverlay.PeekingAnimator;

public abstract class BaseLayout extends RelativeLayout implements Layout {

    public BaseLayout(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void showSlidingOverlay(final SlidingOverlayParams params) {
        final ContentView view = createSlidingView(params);
        addView(view);

        final PeekingAnimator animator = new PeekingAnimator(view);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
                removeView(view);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                removeView(view);
            }

            @Override public void onAnimationRepeat(Animator animator) {}
            @Override public void onAnimationStart(Animator animator) {}
        });
        view.setOnDisplayListener(new Screen.OnDisplayListener() {
            @Override
            public void onDisplay() {
                view.setVisibility(View.VISIBLE);
                animator.animate();
            }
        });
    }

    protected ContentView createSlidingView(SlidingOverlayParams params) {
        final float heightPixels = ViewUtils.convertDpToPixel(100);

        final LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, (int) heightPixels); // TODO
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        final ContentView view = new ContentView(getActivity(), params.screenInstanceId, params.navigationParams);
        view.setLayoutParams(lp);
        view.setVisibility(View.INVISIBLE);
        return view;
    }

    protected AppCompatActivity getActivity() {
        return (AppCompatActivity) getContext();
    }
}
