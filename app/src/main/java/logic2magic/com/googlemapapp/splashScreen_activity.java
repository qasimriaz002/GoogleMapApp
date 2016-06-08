package logic2magic.com.googlemapapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen_activity extends AppCompatActivity {

    ImageView imageView;
    TextView textView_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        imageView = (ImageView) findViewById(R.id.imageView_logo);
        textView_name = (TextView) findViewById(R.id.tv_name);
        Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation_rotate);


//        Animation an = new RotateAnimation(0,360);
//        an.setDuration(2000);
        imageView.startAnimation(an);



        Animation tv_animation = new AlphaAnimation(0.0f,0.9f);
        tv_animation.setDuration(2500);
        textView_name.startAnimation(tv_animation);

        final Intent intent = new Intent(this, MainActivity.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }, 2600);
    }

}
