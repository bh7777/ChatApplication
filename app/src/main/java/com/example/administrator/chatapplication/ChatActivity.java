package com.example.administrator.chatapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity  {
    private EditText mInputMessage;
    private Button mSendButton;
    private LinearLayout mMessageLog;
//    private TextView mCpuMessage;
//    private TextView mUserMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mInputMessage = (EditText) findViewById(R.id.input_message);
        mSendButton = (Button) findViewById(R.id.send_button);
        mMessageLog = (LinearLayout) findViewById(R.id.message_log);
//        mCpuMessage = (TextView) findViewById(R.id.cpu_message);
//        mUserMessage = (TextView) findViewById(R.id.user_message);

        mInputMessage.setText("hoge");


        //mSendButton.setOnClickListener(this);
    }

    public void sendMessage(View v) {
        String inputText = mInputMessage.getText().toString();
        String lowerInputText = inputText.toLowerCase();
        String answer;
        TextView userMessage = new TextView(this);
        int messageColor = getResources().getColor(R.color.message_color,getTheme());
                //getColor(R.color.message_color);
        userMessage.setTextColor(messageColor);

        userMessage.setBackgroundResource(R.drawable.user_message);
        userMessage.setText(inputText);
        //userMessage.setGravity(Gravity.END);
        LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        userMessageLayout.gravity = Gravity.END;
        final int marginSize = getResources().getDimensionPixelSize(R.dimen.message_margin);
        userMessageLayout.setMargins(0,marginSize,0,marginSize);
        mMessageLog.addView(userMessage,0,userMessageLayout);


//        mUserMessage.setText(inputText);
        if (lowerInputText.contains(getString(R.string.how_are_you))) {
                answer = getString(R.string.fine);
        } else if (lowerInputText.contains(getString(R.string.tire))) {
            answer = getString(R.string.bless_you);
        } else if (lowerInputText.contains(getString(R.string.time))) {
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            answer = String.format(getString(R.string.time_format),hour,minute,second);

        } else if (inputText.contains("운세")) {
            double random = Math.random() *5.1d;
            if (random < 1d ) {
                answer = "나쁨";
            } else if (random < 2d ) {
                answer = "보통";
            } else if (random < 3d ) {
                answer = "좋음";
            } else if (random < 4d ) {
                answer = "아주좋음";
            } else if (random < 5d ) {
                answer = "대박";
            } else {
                answer = "운수대통";
            }
        } else {
            answer = "ㅋㅋㅋㅋ";
        }




        final TextView cpuMessage = new TextView(this);
        cpuMessage.setTextColor(messageColor);
        cpuMessage.setBackgroundResource(R.drawable.cpu_message);
        cpuMessage.setText(answer);
        cpuMessage.setGravity(Gravity.START);

        mInputMessage.setText("");


        TranslateAnimation userMessageAnimation = new TranslateAnimation(mMessageLog.getWidth(),0,0,0);
        userMessageAnimation.setDuration(1000);
        userMessageAnimation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LinearLayout.LayoutParams cpuMessageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                cpuMessageLayout.gravity = Gravity.START;
                mMessageLog.addView(cpuMessage,0,cpuMessageLayout);
                TranslateAnimation cpuAnimation = new TranslateAnimation(-mMessageLog.getWidth(),0,0,0);
                cpuAnimation.setDuration(500);
                cpuMessage.setAnimation(cpuAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        userMessage.startAnimation(userMessageAnimation);


    }
}
