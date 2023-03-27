package com.omshanti.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.omshanti.workout.adapter.OnboardingAdapter;
import com.omshanti.workout.component.AppEnv;
import com.omshanti.workout.model.Onboarding;

import java.util.ArrayList;
import java.util.List;

public class WelcomScreenActivity extends AppCompatActivity {
    AppEnv appEnv;
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private Button buttonOnboardingAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_screen);
        appEnv = (AppEnv) getApplicationContext();
        //check
        if (!appEnv.sharePerference.getFirstTimeStart()){
            startMainActivity();
            finish();
        }

        layoutOnboardingIndicator = (LinearLayout) findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = (Button) findViewById(R.id.buttonOnBoardingAction);
        setOnboardingItem();
        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);
        setOnboadingIndicator();
        setCurrentOnboardingIndicators(0);
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startMainActivity();
                }
            }
        });
    }
    private void startMainActivity() {
        appEnv.sharePerference.setFirstTimeStart(false);
        startActivity(new Intent(WelcomScreenActivity.this, FilterContainerActivity.class));
        finish();
    }
//    private boolean firsttimestartapp() {
//        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSlideApp", Context.MODE_PRIVATE);
//        return ref.getBoolean("FirstTimeStartFlag", true);
//    }
//    private void setFirstTimeSrart(boolean sfts){
//        SharedPreferences ref  = getApplicationContext().getSharedPreferences("IntroSlideApp", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = ref.edit();
//        editor.putBoolean("FirstTimeStartFlag", sfts);
//        editor.commit();
//    }

    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_circle_24));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_outline_circle_24));
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1){
            buttonOnboardingAction.setText(R.string.start);
        }else {
            buttonOnboardingAction.setText(R.string.next);
        }
    }

    private void setOnboadingIndicator() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.ic_outline_circle_24
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    private void setOnboardingItem() {
        List<Onboarding> onBoardingItems = new ArrayList<>();

        Onboarding itemFastFood = new Onboarding();
        itemFastFood.setTitle("Workout anywhere");
        itemFastFood.setDescription("You can do your workout at home without amy equipments, outside or at the gym.");
        itemFastFood.setImage(R.drawable.dummy_img);

        Onboarding itemPayOnline = new Onboarding();
        itemPayOnline.setTitle("Learn techniques");
        itemPayOnline.setDescription("Our workout program are made by professional.");
        itemPayOnline.setImage(R.drawable.dummy_img);

        Onboarding itemEatTogether = new Onboarding();
        itemEatTogether.setTitle("Stay strong & healthy");
        itemEatTogether.setDescription("We want you to fully enjoy the program and stay healthy and positive.");
        itemEatTogether.setImage(R.drawable.dummy_img);

        onBoardingItems.add(itemFastFood);
        onBoardingItems.add(itemPayOnline);
        onBoardingItems.add(itemEatTogether);

        onboardingAdapter = new OnboardingAdapter(onBoardingItems);
    }
}