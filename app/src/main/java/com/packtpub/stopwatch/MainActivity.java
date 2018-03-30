package com.packtpub.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {

    private int milliseconds=0;
    private boolean running;
    private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            milliseconds=savedInstanceState.getInt("milliseconds");
            running=savedInstanceState.getBoolean("running");
            wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runtimer();
    }
    public void start(View view)
    {
        ImageView image=(ImageView) findViewById(R.id.imageView);
        int logo=R.drawable.logo;
        String description="@string/description";
        image.setImageResource(logo);
        image.setContentDescription(description);
        running=true;
    }
    public void stop(View view)
    {
        running=false;
    }
    public void reset(View view)
    {
        running=false;
        milliseconds=0;
    }
    private void runtimer()
    {
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                TextView watch=(TextView) findViewById(R.id.time);
                int minutes=milliseconds/60000;
                int secs=(milliseconds%60000)/100;
                int millsecs=(milliseconds%100);
                String time=String.format("%02d:%02d:%02d",minutes,secs,millsecs);
                watch.setText(time);
                if(running)
                    milliseconds++;
                handler.postDelayed(this,1);
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("milliseconds",milliseconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasruning",wasrunning);
    }
    @Override
    public void onStart()
    {
        super.onStart();
        if(wasrunning)
            running=true;
    }
    public void onStop()
    {
        super.onStop();
        if(running)
        wasrunning=true;
        else
        wasrunning=false;
    }
}
