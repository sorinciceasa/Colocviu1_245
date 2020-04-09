package ro.pub.cs.systems.eim.colocviu1_245;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.colocviu1_245.general.Constants;

public class Colocviu1_245MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button computeButton;
    private EditText inputText;
    private EditText bufferedText;

    private int savedSum = 0;

    private int serviceStatus = Constants.SERVICE_STOPPED;

    private String buffer = "";

    private IntentFilter intentFilter = new IntentFilter();


    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.Add:
                    //bufferedText.setText(Constants.INPUT);

                    if(inputText.getText().toString() != null){
                        if(bufferedText.getText().toString() == "" || buffer == null)
                            buffer = inputText.getText().toString();
                        else
                            buffer += "+" + inputText.getText().toString();
                        bufferedText.setText(buffer);
                    }
                    break;
                case R.id.Compute:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_245SecondaryActivity.class);
                    String[] nums = buffer.split("/+");
                    int sum = 0;
                    for (String s : nums)
                        sum += Integer.parseInt(s);

                    intent.putExtra(Constants.SUM, sum);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_245_main);

        addButton = (Button)findViewById(R.id.Add);
        addButton.setOnClickListener(buttonClickListener);

        computeButton = (Button)findViewById(R.id.Compute);
        computeButton.setOnClickListener(buttonClickListener);

        inputText = (EditText)findViewById(R.id.InputText);
        bufferedText = (EditText)findViewById(R.id.Disabledtext);

        buffer = null;

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.INPUT)) {
                inputText.setText(savedInstanceState.getString(Constants.INPUT));
            } else {
                inputText.setText("");
            }
            if (savedInstanceState.containsKey(Constants.BUFFER)) {
                bufferedText.setText(savedInstanceState.getString(Constants.BUFFER));
            } else {
                bufferedText.setText("");
            }
        } else {
            inputText.setText("");
            bufferedText.setText("");
        }


        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(Constants.BUFFER, bufferedText.getText().toString());
        savedInstanceState.putString(Constants.INPUT, inputText.getText().toString());
        savedInstanceState.putString(Constants.SAVED_SUM, "" + savedSum);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.BUFFER)) {
            bufferedText.setText(savedInstanceState.getString(Constants.BUFFER));
        } else {
            bufferedText.setText("");
        }
        if (savedInstanceState.containsKey(Constants.INPUT)) {
            bufferedText.setText(savedInstanceState.getString(Constants.INPUT));
        } else {
            inputText.setText("");
        }
    }


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_245Service.class);
        stopService(intent);
        super.onDestroy();
    }

    //afiseaza returnul intentiei
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            savedSum = resultCode;

            if (savedSum > 10  && serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent2 = new Intent(getApplicationContext(), Colocviu1_245Service.class);
                intent.putExtra(Constants.SAVED_SUM, savedSum);
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
            }

        }
    }
}
