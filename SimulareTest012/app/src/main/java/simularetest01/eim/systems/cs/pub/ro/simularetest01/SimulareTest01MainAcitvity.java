package simularetest01.eim.systems.cs.pub.ro.simularetest01;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimulareTest01MainAcitvity extends AppCompatActivity {

    private TextView text_left =  null;
    private TextView text_right = null;
    private Button button_left = null, button_right = null, navigate_button = null;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();

    private int clicks_left = 0, clicks_right = 0;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_left:
                    clicks_left++;
                    text_left.setText(String.valueOf(clicks_left));
                    break;
                case R.id.button_right:
                    clicks_right++;
                    text_right.setText(String.valueOf(clicks_right));
                    break;
                case R.id.navigate_button:
                    Intent intent = new Intent (getApplicationContext(), SecondaryActivity.class);
                    intent.putExtra("clicks_left", String.valueOf(clicks_left));
                    intent.putExtra("clicks_right",String.valueOf(clicks_right));
                    startActivityForResult(intent, Constants.CodeSecondApp);
                    break;
            }

            if( !Constants.ServiceStarted && clicks_left + clicks_right > Constants.ServiceThreshold) {
                Constants.ServiceStarted = true;
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("left_clicks", String.valueOf(clicks_left));
                intent.putExtra("right_clicks", String.valueOf(clicks_right));
                startService(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulare_test01_main_acitvity);

        text_left = findViewById(R.id.text_left);
        text_right = findViewById(R.id.text_right);
        button_left = findViewById(R.id.button_left);
        button_right = findViewById(R.id.button_right);
        navigate_button = findViewById(R.id.navigate_button);

        button_right.setOnClickListener(buttonClickListener);
        button_left.setOnClickListener(buttonClickListener);
        navigate_button.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if(savedInstanceState.containsKey("left_text")) {
                text_left.setText(savedInstanceState.getString("left_text"));
            }
            else {
                text_left.setText("0");
            }
            if(savedInstanceState.containsKey("right_text")) {
                text_right.setText(savedInstanceState.getString("right_text"));
            }
            else {
                text_right.setText("0");
            }
        }
        else {
            text_right.setText("0");
            text_left.setText("0");
        }

        for(int idx = 0; idx < Constants.actions.length; idx++) {
            intentFilter.addAction(Constants.actions[idx]);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("left_text", String.valueOf(clicks_left));
        outState.putString("right_text", String.valueOf(clicks_right));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(null != savedInstanceState) {
            if(savedInstanceState.containsKey("left_text")) {
                text_left.setText(savedInstanceState.getString("left_text"));
            }
            else {
                text_left.setText("0");
            }
            if(savedInstanceState.containsKey("right_text")) {
                text_right.setText(savedInstanceState.getString("right_text"));
            }
            else {
                text_right.setText("0");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.CodeSecondApp) {
            Toast.makeText(getApplicationContext(), "Secondary Activity returned with result code: " + resultCode,
                    Toast.LENGTH_LONG).show();
        }
    }
}
