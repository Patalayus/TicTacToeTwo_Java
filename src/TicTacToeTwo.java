

import java.util.Arrays;
import java.util.Scanner;

/** By Luke Charles - 14/10/2017
 *  Java TicTacToe 2
 */

class Gameboard{
    private char[][] gameBoard;
    private boolean gameOnGoing = true;

        public Gameboard(){
            gameBoard = new char[3][3];


            for (int row=0; row < gameBoard.length; row++){
                Arrays.fill(gameBoard[row], ' ');
            }
        }

        public void displayBoard(){
            for(int row=0; row < gameBoard.length; row++){
                for(int col=0; col < gameBoard[0].length;col++){
                    System.out.print("\t"+gameBoard[row][col]);
                    if(col == 0 || col == 1){
                        System.out.print("|");
                    }
                }
                if(row == 0 || row == 1)
                System.out.print("\n---------------\n");

            }
            System.out.println();
        }

        public boolean gameActive(){
            return gameOnGoing;
        }

        public void askPlayer(char player){
            Scanner keyboard = new Scanner(System.in);
            int row, col;
            do {
                System.out.print("Please enter a row(1-3): ");
                row = keyboard.nextInt();

                System.out.print("Please enter a column (1-3): ");
                col = keyboard.nextInt();
            }while (notValid(row,col));
            MakeMove(player,row,col);
        } //above method will ask the user for a row and column to select their position


    public boolean notValid(int row, int col){
            if(row>3||row<1)
                return true;
            if(col>3||col<1)
                return true;
            return false;
    }


        public boolean MakeMove(char player, int row, int col){
            if(row>=0 && row <=2 && col>=0 && col <= 2){
                if(gameBoard[row][col]!=' ')
                    return false;
                else {
                    gameBoard[row][col] = player;
                    return true;
                }
            }
            else return false;
        }// this method above executes when the player makes a move.

    public static void main(String args[]){
        Gameboard thisGameBoard = new Gameboard();
        int counter = 1;

        while(thisGameBoard.gameActive()){
         if(counter % 2 == 0){
            thisGameBoard.askPlayer('O');
         }else
             thisGameBoard.askPlayer('X');
        }

        //thisGameBoard.MakeMove('O',0,0);
        //thisGameBoard.MakeMove('X',1,1);
        System.out.println("\n");
        thisGameBoard.displayBoard();
    }
}