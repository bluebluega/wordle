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

    /**
     * Checks whether or not the game is over
     */
    public boolean gameNotOver(){
        return numGuesses<6;
    }

    /**
     * Checks whether or not the game is solved
     */
    public boolean isUnsolved(){
        return numCorrectGuesses<5;
    }

    /**
     * Evaluates the guess and shows how correct the guess is
     */
    public void evaluate(String g){
        numCorrectGuesses=0;

        if(g.length()!=5){
            System.out.println("Guess must be 5 letters. Try again.");
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
                boolean criteria = true;
                for(int i=0; i<g.length(); i++){
                    String l = g.substring(i,i+1);

                    if(g.indexOf(l)!=g.lastIndexOf(l)){   
                        criteria = false;
                    }
                }
                
                if (!criteria){
                    System.out.println("Guess cannot include repeating letters. Try again.");
                } else System.out.println("Guess must be an existing word. Try again.");
            }
        }
        System.out.println("\n");
    }

    /**
     * Checks whether or not the guess is part of the word.txt file
     */
    public boolean isWord(String g){
        boolean isrealWord = false;
        if (vocabList.contains(g.toLowerCase())){
            isrealWord = true;
        }
        return isrealWord;
    }

    /**
     * Inputs all the five letter words w/ non repeating letters into the arraylist vocabList
     */
    public void loadWords(String filename){
        String ans = "";
        File wordfile = new File(filename);
        try {
            Scanner fileScanner = new Scanner(wordfile);
            while (fileScanner.hasNext()){
                String w = fileScanner.nextLine();
                if(w.length()==5 && !Character.isUpperCase(w.charAt(0))){
                    boolean criteria = true;

                    for(int i=0; i<w.length(); i++){
                        String l = w.substring(i,i+1);

                        if(w.indexOf(l)!=w.lastIndexOf(l)){   
                            criteria = false;
                        }
                    }

                    if(criteria){
                        vocabList.add(w);
                    }
                }
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }

    /**
     * Loads arraylist with all the letters
     */
    public void loadAlpha(){
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i=0; i<alpha.length(); i++){
            alphabet.add(alpha.substring(i,i+1));
        }
    }

    /**
     * Adds letters to the correctly guessed letters list
     */
    public void addLetter(String l){
        if(!lettersInWord.contains(l)){
            lettersInWord.add(l);
        }
    }

    /**
     * Removes letters that have been used
     */
    public void removeLetter(String l){
        if(alphabet.contains(l)){
            alphabet.remove(alphabet.indexOf(l));
        }
    }

    /**
     * Returns the word
     */
    public String getWord(){
        return word;
    }
}