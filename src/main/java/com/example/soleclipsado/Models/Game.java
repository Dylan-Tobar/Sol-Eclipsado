package com.example.soleclipsado.Models;

/**
 * Class responsible for the game´s logic.
 * @author Dylan-Tobar, Ricardo-Hallado
 * @version 1.0
 *
 */

public class Game {

    private String secretWord;
    private int countError;
    private int countWin;
    private int assistance;
    private boolean[] guessLetter;

    /**
     * Constructor method
     * @param secretWord The secret World that matches the letters.
     */
    public Game(String secretWord) {
        this.secretWord = secretWord;
        countError = 0;
        countWin = 0;
        assistance = 3;
        guessLetter = new boolean[secretWord.length()];
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
    private char convertToLower(char letter) {
        switch (letter) {
            case 'A': return 'a';
            case 'B': return 'b';
            case 'C': return 'c';
            case 'D': return 'd';
            case 'E': return 'e';
            case 'F': return 'f';
            case 'G': return 'g';
            case 'H': return 'h';
            case 'I': return 'i';
            case 'J': return 'j';
            case 'K': return 'k';
            case 'L': return 'l';
            case 'M': return 'm';
            case 'N': return 'n';
            case 'Ñ': return 'ñ';
            case 'O': return 'o';
            case 'P': return 'p';
            case 'Q': return 'q';
            case 'R': return 'r';
            case 'S': return 's';
            case 'T': return 't';
            case 'U': return 'u';
            case 'V': return 'v';
            case 'W': return 'w';
            case 'X': return 'x';
            case 'Y': return 'y';
            case 'Z': return 'z';
            default: return letter;
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
        char normalLetter = converLetter(convertToLower(letter));
        char normalUserLetter = converLetter(convertToLower(userLetter));
        if (normalLetter == normalUserLetter){
            if(!guessLetter[pos]){
                countWin++;
                guessLetter[pos] = true;
            }
            return true;
        } else {
            countError++;
            return false;
        }
    }

    /**
     * Method that determines whether I won the game.
     * @return True if I guess everything correctly, false otherwise.
     */
    public boolean winParty() {
        if (countWin == secretWord.length()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that determines whether you lost the game.
     * @return True if there are more than 5 errors, false otherwise.
     */

    public boolean loseParty() {
        if (countError >= 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that provides a hint.
     * @param pos Letter position
     * @return
     */
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

