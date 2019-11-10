package com.simbirsoft.marat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        /*В ходе разметки я что то поломал, и андроед никак не хочет брать примариДарк для
        разукрашивания статусБара. Понимаю что дикий костыль, но это только самое начало моего
        обучения, и если вы мне подскажете в чем ошибка, я был бы вам очень благодарен :)
         */
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        ProgressBar progressBar = findViewById(R.id.pb_splash);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2*1000);


    }

}
