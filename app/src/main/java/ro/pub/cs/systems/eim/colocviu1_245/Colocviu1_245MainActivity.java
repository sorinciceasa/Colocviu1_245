package ro.pub.cs.systems.eim.colocviu1_245;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_245MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button computeButton;
    private EditText inputText;
    private EditText bufferedText;

    private String buffer;

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

            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_245_main);

        addButton = (Button)findViewById(R.id.Add);
        addButton.setOnClickListener(buttonClickListener);

        inputText = (EditText)findViewById(R.id.InputText);
        bufferedText = (EditText)findViewById(R.id.Disabledtext);

        buffer = null;

    }
}
