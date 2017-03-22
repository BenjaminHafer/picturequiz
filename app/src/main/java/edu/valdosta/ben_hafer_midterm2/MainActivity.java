package edu.valdosta.ben_hafer_midterm2;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;




public class MainActivity extends AppCompatActivity {



    private String word;
    private String answer;
    private String scrambled;
    private String[] names = new String[]{"apple","ball","bike","clown"};
    private int previousChoice;
    private String responses;
    private int imageResource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            word = savedInstanceState.getString("word");
            answer = savedInstanceState.getString("answer");
            previousChoice = savedInstanceState.getInt("previousChoice");
            scrambled = savedInstanceState.getString("scrambled");
            responses = savedInstanceState.getString("responses");
            imageResource = savedInstanceState.getInt("image");


            //restore text
            ImageView pic = (ImageView)findViewById(R.id.imageView1);
            Drawable res = getResources().getDrawable(imageResource);
            pic.setImageDrawable(res);
            TextView scram = (TextView) findViewById(R.id.scrambledletters);
            scram.setText(scrambled);

            TextView response = (TextView)findViewById(R.id.response);
            response.setText(responses);


        }
        else {
            setImage();
        }

    }

    protected  void onClickGetNewWord(View view) {
        setImage();
        resetInputs();
    }

    protected void onClickCheck(View view) {
        EditText input = (EditText)findViewById(R.id.answer);
        answer = input.getText().toString().toLowerCase();
        TextView response = (TextView)findViewById(R.id.response);
        if(answer.equals(word)){
            responses = "Yay you got it correct!!";
            response.setText(responses);
        }
        else{
            responses = "Try again.";
            response.setText(responses);
        }
    }

    protected void setImage(){
        WordShuffler shuffler = new WordShuffler();
        Random rand = new Random();
        //use this code block to make sure don't show the same picture back to back
        int whichpic=previousChoice;
        while(whichpic == previousChoice) {
            whichpic = rand.nextInt(4);
        }
        previousChoice = whichpic;

        //here we get a little creative randomly choosing an image
        ImageView pic = (ImageView)findViewById(R.id.imageView1);
        String image = "@drawable/" + names[whichpic];
        imageResource = getResources().getIdentifier(image, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        pic.setImageDrawable(res);

        //set the new word value and scramble up the new letters! reset the views
        word = names[whichpic];
        scrambled = shuffler.shuffle(word);
        TextView scram = (TextView)findViewById(R.id.scrambledletters);
        scram.setText(scrambled);
    }

    protected void resetInputs() {
        TextView response = (TextView)findViewById(R.id.response);
        response.setText("");
        EditText answer = (EditText)findViewById(R.id.answer);
        answer.setText("");

    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("word", word);
        savedInstanceState.putString("answer", answer);
        savedInstanceState.putInt("previousChoice", previousChoice);
        savedInstanceState.putStringArray("names",names);
        savedInstanceState.putString("scrambled",scrambled);
        savedInstanceState.putString("responses",responses);
        savedInstanceState.putInt("image",imageResource);

    }

    public void onResume() {
        super.onResume();
    }

}