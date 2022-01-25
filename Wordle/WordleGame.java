import java.util.*;

public class WordleGame {
    
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Puzzle puzzle = new Puzzle ();
        instructions();
        
        
        while(puzzle.isUnsolved() && puzzle.gameNotOver()){
            System.out.print("\nMake a guess: ");
            String guess = scanner.nextLine().toUpperCase();
            puzzle.evaluate(guess);
        }
        
        if(!puzzle.isUnsolved()){
            System.out.println("\\(^O^)/ You win!");
            runAgain();
        } else {
            System.out.println("(╯°□°)╯︵ ┻━┻ You lose! The word was " + puzzle.getWord());
            runAgain();
        }
    }
    
    /**
     * Runs/ends the program once the game is over
     */
    public static void runAgain(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Play again? (Type \"y\" or \"n\")");
        String again = scanner.nextLine().toUpperCase();
        
        if(again.equals("Y")){
            System.out.println("\n");
            main(null);
        } else {
            System.out.println("\nGAME OVER. Thank you for playing!");
        }
    }

    public static void instructions(){
        System.out.println("WELCOME TO THE GAME OF WORDLE! \n");
        System.out.println("Instructions:");
        System.out.println("You have 6 chances to guess a 5 letter word. \nEach guess must be an existing 5 letter word that has no repeating letters.");
        System.out.println("\"?\" = the letter is NOT part of the word.");
        System.out.println("\"*\" = the letter is part of the word but is in the wrong location.");
        System.out.println("\"!\" = the letter is part of the word AND is in the correct location. \n");
        System.out.println("After each guess, you will be shown all the letters that you have not yet used & the letters that you have guessed correctly. Good luck!");
    }
}
