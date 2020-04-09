package ro.pub.cs.systems.eim.colocviu1_245;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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

    private String buffer;

    private IntentFilter intentFilter = new IntentFilter();


    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.Add:
                    if(inputText.getText().toString() != null){
                        if(buffer == null)
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

    //afiseaza returnul intentiei
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
