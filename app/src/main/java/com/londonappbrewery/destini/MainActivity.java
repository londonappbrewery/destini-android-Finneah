package com.londonappbrewery.destini;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    TextView mStoryTextView;
    Button mTopButton, mBottomButton, mRestartButton;
    int mStoryIndex;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryIndex", mStoryIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStoryTextView = findViewById(R.id.storyTextView);
        mTopButton = findViewById(R.id.buttonTop);
        mBottomButton = findViewById(R.id.buttonBottom);
        mRestartButton = findViewById(R.id.buttonRestart);

        if (savedInstanceState != null) {
            mStoryIndex = savedInstanceState.getInt("StoryIndex");
            updateStory();
        } else {
            mStoryIndex = checkStoryPart();
            updateStory();
        }


        mTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStoryIndex == 1 || mStoryIndex == 2) {
                    mStoryIndex = 3;
                    updateStory();
                } else {
                    mStoryTextView.setText(R.string.T6_End);
                    showToast();
                }
            }
        });

        mBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStoryIndex == 1) {
                    mStoryIndex = 2;
                    updateStory();
                } else if (mStoryIndex == 2) {
                    mStoryTextView.setText(R.string.T4_End);
                    showToast();
                } else {
                    mStoryTextView.setText(R.string.T5_End);
                    showToast();
                }
            }
        });

        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomButton.setVisibility(View.VISIBLE);
                mTopButton.setVisibility(View.VISIBLE);
                mRestartButton.setVisibility(View.INVISIBLE);
                mStoryIndex = 1;
                mStoryTextView.setText(R.string.T1_Story);
                mTopButton.setText(R.string.T1_Ans1);
                mBottomButton.setText(R.string.T1_Ans2);
            }
        });
    }


    private int checkStoryPart() {
        String storyText = mStoryTextView.getText().toString();
        if (storyText.equals(getResources().getString(R.string.T1_Story))) {
            return 1;
        } else if (storyText.equals(getResources().getString(R.string.T2_Story))) {
            return 2;
        } else {
            return 3;
        }
    }

    private void updateStory() {
        if (mStoryIndex == 1) {
            mStoryTextView.setText(R.string.T1_Story);
            mTopButton.setText(R.string.T1_Ans1);
            mBottomButton.setText(R.string.T1_Ans2);
        } else if (mStoryIndex == 2) {
            mStoryTextView.setText(R.string.T2_Story);
            mTopButton.setText(R.string.T2_Ans1);
            mBottomButton.setText(R.string.T2_Ans2);
        } else {
            // 3
            mStoryTextView.setText(R.string.T3_Story);
            mTopButton.setText(R.string.T3_Ans1);
            mBottomButton.setText(R.string.T3_Ans2);
        }
    }

    private void showToast() {
        mBottomButton.setVisibility(View.INVISIBLE);
        mTopButton.setVisibility(View.INVISIBLE);
        mRestartButton.setVisibility(View.VISIBLE);
        Context context = getApplicationContext();
        CharSequence text = "The End";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
