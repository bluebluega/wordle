import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Puzzle{

    private String word;
    private int numGuesses=0;
    private int numCorrectGuesses=0;
    private ArrayList<String> vocabList;
    private ArrayList<String> alphabet;
    private ArrayList<String> lettersInWord;
    private int index;

    public Puzzle(){
        vocabList = new ArrayList<String>();
        loadWords("words.txt");
        word = vocabList.get((int)(Math.random()*vocabList.size())).toUpperCase();
        alphabet = new ArrayList<String>();
        loadAlpha();
        lettersInWord = new ArrayList<String>();
    }

    public boolean gameNotOver(){
        return numGuesses<6;
    }

    public boolean isUnsolved(){
        return numCorrectGuesses<5;
    }

    public void evaluate(String g){
        numCorrectGuesses=0;
        if(g.length()!=5){
            System.out.println("Guess must be 5 letters. Try again");
        } else{
            if(isWord(g)){
                numGuesses++;
                System.out.print("\nGuess #" + numGuesses + ": ");
                for(int i=0; i<g.length(); i++){
                    String wLetter = word.substring(i, i+1);
                    String gLetter = g.substring(i, i+1);

                    if(wLetter.equals(gLetter)){
                        numCorrectGuesses++;
                        System.out.print(gLetter + "!  ");
                        addLetter(gLetter);
                        removeLetter(gLetter);
                    } else if(!wLetter.equals(gLetter) && word.contains(gLetter)){
                        System.out.print(gLetter + "*  ");  
                        addLetter(gLetter);
                        removeLetter(gLetter);
                    } else {
                        System.out.print(gLetter + "?  ");
                        removeLetter(gLetter);
                    }
                } 
                System.out.println("\nLetters not yet used: " + alphabet);
                System.out.println("Correctly guessed letters: " + lettersInWord);
            }
            else {
                System.out.println("Guess must be an existing word. Try again");
            }
        }
        //System.out.println("Correct guess: " + numCorrectGuesses);
        //System.out.println("Number of guesses: " + numGuesses);
        //System.out.println("Is the game still going?"+ gameNotOver());
        //System.out.println("Is the game UNsolved?"+isUnsolved());
        System.out.println("\n");
    }

    public boolean isWord(String g){
        boolean isrealWord = false;
        if (vocabList.contains(g.toLowerCase())){
            isrealWord = true;
        }
        return isrealWord;
    }

    public void loadWords(String filename){
        String ans = "";
        File wordfile = new File(filename);
        try {
            Scanner fileScanner = new Scanner(wordfile);
            while (fileScanner.hasNext()){
                String w = fileScanner.nextLine();
                if(w.length()==5 && !Character.isUpperCase(w.charAt(0))){
                    vocabList.add(w);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    public void loadAlpha(){
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i=0; i<alpha.length(); i++){
            alphabet.add(alpha.substring(i,i+1));
        }
    }

    public void addLetter(String l){
        if(!lettersInWord.contains(l)){
            lettersInWord.add(l);
        }
    }

    public void removeLetter(String l){
        if(alphabet.contains(l)){
            alphabet.remove(alphabet.indexOf(l));
        }
    }

    public String getWord(){
        return word;
    }
}