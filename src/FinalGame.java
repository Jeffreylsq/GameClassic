import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class FinalGame {

    
    private static String[] Choose = {"Rock", "Paper", "Scissors"};
    private static int AWins = 0, BWins = 0, CWins = 0;
    private static Integer port = 1119;
    private static String host = "localhost";
    
    public static void main(String[] args) throws Exception {
    	
    	System.out.println("Welcome to Classic ROCK-PAPER-SCISSORS game");
        System.out.println("How many times do you want to play");
        Scanner kb =new Scanner(System.in);
        int num = kb.nextInt();

        try {
            for (int i = 0; i < num; i++) {
                

                Socket guess = new Socket(FinalGame.host, FinalGame.port);
                DataOutputStream data = new DataOutputStream(guess.getOutputStream());

                String input = Choose[(int) (Math.random() * 3)];

                data.writeBytes(input + "\n");
                System.out.println(" choice " + input +  " was  transmitted.");

                
                guess.close();// Close socket
            }

        } catch (ConnectException e) {
            String guessAChoice;
            String guessBChoice;

            ServerSocket Socket1 = new ServerSocket(FinalGame.port);
          // This is big for loop to run (num) times to play this game--------------------------
            for (int i = 0; i < num; i++) {
                //-------------- Guess A--------------------------------------------------------
                Socket A = Socket1.accept();
                BufferedReader AInput = new BufferedReader(new InputStreamReader(A.getInputStream()));

                //------------------ Guess B-----------------------------------------
                Socket B = Socket1.accept();
                BufferedReader BInput = new BufferedReader(new InputStreamReader(B.getInputStream()));

                //----------- Guess C------------------------------- 
                String guessCChoice = Choose[(int) (Math.random() * 3)]; 
                guessAChoice = AInput.readLine();
                guessBChoice = BInput.readLine();

                System.out.println("_______This is round_____ " + (i + 1) + " ________");
             //Game between Guess A and B include all the situations--------------------------
                if (guessAChoice.equals(guessBChoice)) {
                    System.out.println("GUESS A and GUESS B ended in draw.");
                } else if (guessAChoice.equals("Rock") && guessBChoice.equals("Scissors")) {
                    System.out.println("GUESS A win, GUESS B lose.");
                    AWins++;
                } else if (guessAChoice.equals("Scissors") && guessBChoice.equals("Rock")) {
                    System.out.println("GUESS B win, GUESS A lose.");
                    BWins++;
                } else if (guessAChoice.equals("Rock") && guessBChoice.equals("Paper")) {
                    System.out.println("GUESS B win, GUESS A lose.");
                    BWins++;
                } else if (guessAChoice.equals("Paper") && guessBChoice.equals("Rock")) {
                    System.out.println("GUESS A win, GUESS B lose.");
                    AWins++;
                } else if (guessAChoice.equals("Scissors") && guessBChoice.equals("Paper")) {
                    System.out.println("GUESS A win, GUESS B lose.");
                    AWins++;
                } else if (guessAChoice.equals("Paper") && guessBChoice.equals("Scissors")) {
                    System.out.println("GUESS B win, GUESS A lose.");
                    BWins++;
                }
              //Game between Guess C and B include all the situations--------------------------
                if (guessCChoice.equals(guessBChoice)) {
                    System.out.println("GUESS C and GUESS B ended in a draw.");
                } else if (guessCChoice.equals("Rock") && guessBChoice.equals("Scissors")) {
                    System.out.println("GUESS C win, GUESS B lose.");
                    CWins++;
                } else if (guessCChoice.equals("Scissors") && guessBChoice.equals("Rock")) {
                    System.out.println("GUESS C win, GUESS B lose.");
                    CWins++;
                } else if (guessCChoice.equals("Rock") && guessBChoice.equals("Paper")) {
                    System.out.println("GUESS B win, GUESS C lose.");
                    BWins++;
                } else if (guessCChoice.equals("Paper") && guessBChoice.equals("Rock")) {
                    System.out.println("GUESS C win, GUESS B lose.");
                    CWins++;
                } else if (guessCChoice.equals("Scissors") && guessBChoice.equals("Paper")) {
                    System.out.println("GUESS C win, GUESS B lose.");
                    CWins++;
                } else if (guessCChoice.equals("Paper") && guessBChoice.equals("Scissors")) {
                    System.out.println("GUESS B win, GUESS C lose.");
                    BWins++;
                }
              //Game between Guess A and C include all the situations--------------------------
                if (guessAChoice.equals(guessCChoice)) {
                    System.out.println("GUESS A and GUESS C ended in a draw.");
                } else if (guessAChoice.equals("Rock") && guessCChoice.equals("Scissors")) {
                    System.out.println("GUESS A win, GUESS C lose.");
                    AWins++;
                } else if (guessAChoice.equals("Scissors") && guessCChoice.equals("Rock")) {
                    System.out.println("GUESS C win, GUESS A lose.");
                    CWins++;
                } else if (guessAChoice.equals("Rock") && guessCChoice.equals("Paper")) {
                    System.out.println("GUESS C win, GUESS A lose.");
                    CWins++;
                } else if (guessAChoice.equals("Paper") && guessCChoice.equals("Rock")) {
                    System.out.println("GUESS A win, GUESS C lose.");
                    AWins++;
                } else if (guessAChoice.equals("Scissors") && guessCChoice.equals("Paper")) {
                    System.out.println("GUESS A win, GUESS C lose.");
                    AWins++;
                } else if (guessAChoice.equals("Paper") && guessCChoice.equals("Scissors")) {
                    System.out.println("GUESS C win, GUESS A lose.");
                    CWins++;
                }
                A.close();
                B.close();
                System.out.println("___________________________________________________________");

            }

           Socket1.close();

            System.out.println("________________Result_____________________");
            System.out.println("GUESS A Wins: " + AWins);
            System.out.println("GUESS B Wins: " + BWins);
            System.out.println("GUESS C Wins: " + CWins);
        }
    }
}