package com.omshanti.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;
import android.window.SplashScreen;

import com.omshanti.workout.component.AppEnv;

public class SplashScreenActivity extends AppCompatActivity {
    AppEnv appEnv;
    TextView textViewfirst;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appEnv = (AppEnv) getApplicationContext();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textViewfirst = findViewById(R.id.firstLetter);
        startAnimation();
//        textWriter = findViewById(R.id.textWriter);
//        textWriter.setWidth(8)
//                .setDelay(50)
//                .setColor(Color.BLUE)
//                .setConfig(TextWriter.Configuration.INTERMEDIATE)
//                .setSizeFactor(30f)
//                .setLetterSpacing(15f)
//                .setText("JOKER")
//                .setListener(new TextWriter.Listener() {
//                    @Override
//                    public void WritingFinished() {
//                        startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
//                    }
//                })
//                .startAnimation();
        //flash for 5 sec
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, WelcomScreenActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);

        startAppInitialization();
    }

    private void startAppInitialization(){
        thread = new Thread(new AppInitThread());
        thread.start();
        //for internet check you can write here !!!
    }
    private void initEnv(){
        if (!appEnv.getEnvStatus()){
            appEnv.gobalInit();
        }
    }

    private void startAnimation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewfirst.setText("W");
            }
        }, 200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewfirst.append("O");
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewfirst.append("R");
            }
        }, 400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewfirst.append("K");
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewfirst.append("O");
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewfirst.append("U");
            }
        }, 700);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewfirst.append("T");
            }
        }, 800);
    }

    //create a class to use thread
    class AppInitThread implements Runnable {
        @Override
        public void run() {
            initEnv();
        }
    }
}