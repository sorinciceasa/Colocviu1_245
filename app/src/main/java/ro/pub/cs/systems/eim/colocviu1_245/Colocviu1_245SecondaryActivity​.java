package ro.pub.cs.systems.eim.colocviu1_245;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ro.pub.cs.systems.eim.colocviu1_245.general.Constants;

public class Colocviu1_245SecondaryActivity​  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_245_main);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.SUM)) {
            int sum = intent.getIntExtra(Constants.SUM, -1);
                //numberOfClicksTextView.setText(String.valueOf(numberOfClicks));
        }
    }

}
