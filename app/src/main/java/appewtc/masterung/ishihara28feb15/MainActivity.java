package appewtc.masterung.ishihara28feb15;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    //Explicit
    private TextView txtQuestion;
    private ImageView imvIshihara;
    private RadioGroup ragChoice;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnAnswer;
    private int intRadioButton, intIndex, intScore, intUserChoose[], intAnswerTrue[];
    private MyModel objMyModel;
    private String strChoice[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Widget
        initialWidget();

        //setup Array
        setUpArray();

        //Create Button Controller
        createButtonController();

        //Create Radio Controller
        createRadioController();

        //Connected Interface
        connectedInterface();

    }   // onCreate

    private void setUpArray() {

        intUserChoose = new int[10];
        intAnswerTrue = new int[10];

        intAnswerTrue[0] = 1;
        intAnswerTrue[1] = 2;
        intAnswerTrue[2] = 3;
        intAnswerTrue[3] = 1;
        intAnswerTrue[4] = 2;
        intAnswerTrue[5] = 3;
        intAnswerTrue[6] = 1;
        intAnswerTrue[7] = 2;
        intAnswerTrue[8] = 4;
        intAnswerTrue[9] = 4;

    }   // setUpArray

    private void connectedInterface() {

        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                switch (myModel.getIntButton()) {

                    case 2:
                        imvIshihara.setImageResource(R.drawable.ishihara_02);
                        strChoice = getResources().getStringArray(R.array.times2);
                        setChoice();
                        break;
                    case 3:
                        imvIshihara.setImageResource(R.drawable.ishihara_03);
                        strChoice = getResources().getStringArray(R.array.times3);
                        setChoice();
                        break;
                    case 4:
                        imvIshihara.setImageResource(R.drawable.ishihara_04);
                        strChoice = getResources().getStringArray(R.array.times4);
                        setChoice();
                        break;
                    case 5:
                        imvIshihara.setImageResource(R.drawable.ishihara_05);
                        strChoice = getResources().getStringArray(R.array.times5);
                        setChoice();
                        break;
                    case 6:
                        imvIshihara.setImageResource(R.drawable.ishihara_06);
                        strChoice = getResources().getStringArray(R.array.times6);
                        setChoice();
                        break;
                    case 7:
                        imvIshihara.setImageResource(R.drawable.ishihara_07);
                        strChoice = getResources().getStringArray(R.array.times7);
                        setChoice();
                        break;
                    case 8:
                        imvIshihara.setImageResource(R.drawable.ishihara_08);
                        strChoice = getResources().getStringArray(R.array.times8);
                        setChoice();
                        break;
                    case 9:
                        imvIshihara.setImageResource(R.drawable.ishihara_09);
                        strChoice = getResources().getStringArray(R.array.times9);
                        setChoice();
                        break;
                    case 10:
                        imvIshihara.setImageResource(R.drawable.ishihara_10);
                        strChoice = getResources().getStringArray(R.array.times10);
                        setChoice();
                        break;

                }   // switch

            }   // event
        });

    }   // connectedInterface

    private void setChoice() {

        radChoice1.setText(strChoice[0]);
        radChoice2.setText(strChoice[1]);
        radChoice3.setText(strChoice[2]);
        radChoice4.setText(strChoice[3]);

    }   // setChoice

    private void createRadioController() {

        ragChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Sound Effect
                MediaPlayer objMediaPlayer = MediaPlayer.create(getBaseContext(),
                        R.raw.effect_btn_shut);
                objMediaPlayer.start();

                //Setup intRadioButton
                switch (checkedId) {

                    case R.id.radioButton:
                        intRadioButton = 1;
                        break;
                    case R.id.radioButton2:
                        intRadioButton = 2;
                        break;
                    case R.id.radioButton3:
                        intRadioButton = 3;
                        break;
                    case R.id.radioButton4:
                        intRadioButton = 4;
                        break;
                    default:
                        intRadioButton = 0;
                        break;

                }   // switch


            }   // event
        });

    }   // createRadioController

    private void createButtonController() {

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Sound Effect
                MediaPlayer objMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                objMediaPlayer.start();

                // Check No Answer
                if (intRadioButton == 0) {

                    MyAlertDialog objMyAlertDialog = new MyAlertDialog();
                    objMyAlertDialog.noAnswer(MainActivity.this);

                } else {

                    //Check Score
                    checkScore();

                    // Check Times
                    checkTime();

                }   // if


            }   // event
        });

    }   // createButtonController

    private void checkScore() {

        intUserChoose[intIndex] = intRadioButton;

        if (intUserChoose[intIndex] == intAnswerTrue[intIndex]) {
            intScore++;
        }

    }   // checkScore

    private void checkTime() {

        if (intIndex == 9) {

            //Intent to ShowScoreActivity
            Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class);

            //Put Value tor ShowScore
            objIntent.putExtra("Score", intScore);

            startActivity(objIntent);
            finish();

        } else {

            // Controller Call View
            txtQuestion.setText(Integer.toString(intIndex + 2) + ". What is this ?");
            intIndex += 1;

            //Controller Call Model
            objMyModel.setIntButton(intIndex + 1);

        }   // if

    }   // checkTime

    private void initialWidget() {

        txtQuestion = (TextView) findViewById(R.id.textView2);
        imvIshihara = (ImageView) findViewById(R.id.imageView);
        ragChoice = (RadioGroup) findViewById(R.id.ragChoice);
        radChoice1 = (RadioButton) findViewById(R.id.radioButton);
        radChoice2 = (RadioButton) findViewById(R.id.radioButton2);
        radChoice3 = (RadioButton) findViewById(R.id.radioButton3);
        radChoice4 = (RadioButton) findViewById(R.id.radioButton4);
        btnAnswer = (Button) findViewById(R.id.button);

    }   // initialWidget


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class ภาษาไทยก็ได้
