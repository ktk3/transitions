package com.example.kkasia.transitionswscenes;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kkasia.transitionswscenes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout mRootLayout =
                (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null, false);
        final LinearLayout mTranslateLayout =
                (LinearLayout) mRootLayout.findViewById(R.id.translate_layout);

        TextView mInputLanguageView = (TextView) mRootLayout.findViewById(R.id.input_language_text);
        TextView mOutputLanguageView = (TextView) mRootLayout.findViewById(R.id.output_language_text);

        final ImageView mImageView = (ImageView) mRootLayout.findViewById(R.id.image);
        final ImageView mFullScreenImage =  new ImageView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        mFullScreenImage.setLayoutParams(params);
        mFullScreenImage.setAdjustViewBounds(true);
        mFullScreenImage.setImageResource(R.drawable.sunrise);

        // Views with the same IDs get animated between transitions
        mImageView.setId(R.id.image);
        mFullScreenImage.setId(R.id.image);


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TransitionManager.go(new Scene(mRootLayout, mFullScreenImage));
                }
            }
        });
        mFullScreenImage.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TransitionManager.go(new Scene(mRootLayout, (View) mTranslateLayout), new ChangeBounds());
                }
            }
        });
        setContentView(mRootLayout);

    }
}
