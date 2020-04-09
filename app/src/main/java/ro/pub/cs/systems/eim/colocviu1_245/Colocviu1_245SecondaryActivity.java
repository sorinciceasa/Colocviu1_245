package ro.pub.cs.systems.eim.colocviu1_245;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Random;

import ro.pub.cs.systems.eim.colocviu1_245.general.Constants;

public class Colocviu1_245SecondaryActivity extends AppCompatActivity {

    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_245_main);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.SUM)) {
            int sum = intent.getIntExtra(Constants.SUM, -1);

            intent.setAction(Constants.actionTypes[0]);
            intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                    new Date(System.currentTimeMillis()) + " " + sum);
            this.context.sendBroadcast(intent);
        }
    }

}
