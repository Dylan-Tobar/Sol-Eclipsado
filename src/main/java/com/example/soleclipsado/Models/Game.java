package com.example.soleclipsado.Models;

/**
 * Class responsible for the game´s logic.
 * @author Dylan-Tobar
 * @version 1.0
 *
 */

public class Game {

    private String secretWord;
    private int countError;
    private int countWin;
    private int assistance;

    /**
     * Constructor method
     * @param secretWord The secret World that matches the letters.
     */
    public Game(String secretWord) {
        this.secretWord = secretWord;
        countError = 0;
        countWin = 0;
        assistance = 3;
    }

    /**
     * A method that converts accented vowels to their unaccented forms.
     * @param normalize keep the letters without accents
     * @return letters without accents
     */
    private char converLetter(char normalize) {
        switch (normalize) {
            case 'á':
                return 'a';
            case 'é':
                return 'e';
            case 'í':
                return 'i';
            case 'ó':
                return 'o';
            case 'ú':
                return 'u';
            default:
                return normalize;
        }
    }

    /**
     * A method that compares the password with the letters entered by the user.
     * @param userLetter The letter entered by the user.
     * @param pos The position of the letter.
     * @return True if the letter is correct, otherwise false.
     */

    public boolean compareLetter(char userLetter, int pos) {
        char letter = secretWord.charAt(pos);
        char normalLetter = converLetter(letter);
        char normalUserLetter = converLetter(userLetter);
        if (normalLetter == normalUserLetter) {
            countWin++;
            return true;
        } else {
            countError++;
            return false;
        }
    }

    public boolean winParty() {
        if (countWin == secretWord.length()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean loseParty() {
        if (countError >= 5) {
            return true;
        } else {
            return false;
        }
    }
    public char helpUser(int pos){
        if(assistance>0){
            assistance--;
            return secretWord.charAt(pos);
        }
        else{
            return '_';
        }
    }

    public int getCountError(){
        return countError;
    }

    public int getAssistance(){
        return assistance;
    }

}

