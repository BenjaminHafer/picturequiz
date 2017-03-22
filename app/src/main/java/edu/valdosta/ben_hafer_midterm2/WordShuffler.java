package edu.valdosta.ben_hafer_midterm2;

/**
 * Created by the_hound on 3/2/17.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the_hound on 3/2/17.
 */

public class WordShuffler {

    //shuffle method accecpting a string as an input
    public String shuffle(String input){

        //create an arraylist to hold the characters of the string
        List<Character> inputArray = new ArrayList<Character>();

        //loop through the characters in  the string and add them to the arralist
        for(char c:input.toCharArray()){
            inputArray.add(c);
        }

        //create onbject to build our random string from
        StringBuilder output = new StringBuilder(input.length());

        //check that there are still characters unused to randomly choose
        while(inputArray.size()!=0){
            //randomly choose a letter from the list, and remove it after.
            int randPick = (int)(Math.random()*inputArray.size());
            output.append(inputArray.remove(randPick));
        }

        //return the scrambled word
        String scambled = output.toString();
        return scambled;
    }
}