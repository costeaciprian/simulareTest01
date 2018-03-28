package simularetest01.eim.systems.cs.pub.ro.simularetest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    private Button ok_button, cancel_button;
    private TextView out_button;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_ok:
                    setResult(RESULT_OK);
                    finish();
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        out_button = findViewById(R.id.result_text);
        ok_button = findViewById(R.id.button_ok);
        cancel_button = findViewById(R.id.cancel_button);

        ok_button.setOnClickListener(buttonClickListener);
        cancel_button.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        String clicks_left = intent.getStringExtra("clicks_left");
        String clicks_right = intent.getStringExtra("clicks_right");

        out_button.setText(String.valueOf(Integer.parseInt(clicks_left) + Integer.parseInt(clicks_right)));
    }
}
