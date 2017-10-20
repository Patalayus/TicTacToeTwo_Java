

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;

//test commit
/** By Luke Charles - 14/10/2017
 *  Java TicTacToe 2
 */

class Gameboard{
    private char[][] gameBoard;

    private boolean gameOnGoing = true;
    private boolean BotGame = false;
    private boolean BotEmpty = true;

    public String player1name;
    public String player2name;



    private double keeptrack;

    private double enps_win1 = 0;
    //test commit
    //declares variables gameBoard and gameOnGoing.

        public Gameboard(){

            gameBoard = new char[3][3];
            //makes the gameBoard a three by three square.

            for (int row=0; row < gameBoard.length; row++){
                Arrays.fill(gameBoard[row], ' ');
            }
            //the for loop above loops until the row = the gameBoard length, and fills the rows with spaces.
        }

        public void displayBoard(){
            for(int row=0; row < gameBoard.length; row++){
                for(int col=0; col < gameBoard[0].length;col++){
                    System.out.print("\t"+gameBoard[row][col]);
                    if(col == 0 || col == 1){
                        System.out.print("|");
                    }
                    //this for loop creates the columns for the game, using tab spaces.
                }
                if(row == 0 || row == 1)
                System.out.print("\n---------------\n");
                //this creates a visible line separating the rows for the gameBoard.
            }
            System.out.println();
        }

        public boolean gameActive(){
            return gameOnGoing;
        }
        //the above method is used to check if the game is still active at the conclusion of every turn.

        public void askPlayer(char player){
            Scanner keyboard = new Scanner(System.in);
            Gameboard jump = new Gameboard();

            int row_, col_;
            do {
                System.out.printf("Player %s, "+player1name+" Please enter a row (1-3): ", player);
                row_ = keyboard.nextInt();

                System.out.printf("Player %s, "+player2name+" Please enter a column (1-3): ", player);
                col_ = keyboard.nextInt();
            }while (notValid(row_,col_));
            MakeMove(player,row_-1,col_-1);
        } //above method will ask the user for a row and column to select their position.

    public boolean notValid(int row, int col){
            if(row > 3 || row < 1 || col > 3 || col < 1 || !isEmpty(row, col))
                return true;
            // checks the users validity of input, whether the input is either too large or small, and whether it has not been entered.
            else
                return false;
            //based on this variable, the console will prompt the user to enter a valid input.
    }

    public boolean isEmpty(int row, int col){
        if(gameBoard[row-1][col-1]==' '){
            BotEmpty = true;
            return true;
            //this returns true if the place is empty.
        }else{
            System.out.print("That position is taken. \n");
            BotEmpty = false;
            return false;
            //this returns false if the user has entered a position which is already filled from the previous player.
        }
    }

    public boolean checkwin() {
        //loop each row and check for winner
        for (int row = 0; row < gameBoard.length; row++){
            //object 'row' equals 0, and will increment 1 each time until it is equal to the boards length.

            if (gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][1] == gameBoard[row][2] && gameBoard[row][0] != ' '){
                enps_win1+=1;
                System.out.print("The winner is "+gameBoard[row][0]);
                gameOnGoing = false;
                /* if a value in a row is equal to another and this value is also equal to a third, and does not equal a space, will return
                * the winner as the value in these rows.
                */
            }
        }
        for(int col=0;col<gameBoard[0].length;col++){
            //object 'col' equals 0, and will increment 1 each time until it is equal to the boards length.

            if (gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col] && gameBoard[0][col] != ' '){
                enps_win1 +=1;
                System.out.print("The winner is "+gameBoard[0][col]);
                gameOnGoing = false;}
                /* if a value in a column is equal to another and this value is also equal to a third, and does not equal a space, will return
                * the winner as the value in these columns.
                */
        }
        //checking diagonals
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != ' '){
            enps_win1+=1;
            System.out.print("This winner is "+gameBoard[0][0]);
            gameOnGoing = false;
            // as well as checking the columns and rows of the board, the diagonals need to be checked as well.
            //this will check the top left to bottom right.
        }
        if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2] && gameBoard[0][2] != ' '){
            enps_win1+=1;
            System.out.print("This winner is "+gameBoard[1][1]);
            gameOnGoing = false;
            //this will check the top right to the bottom left.
        }

        return false;
        //otherwise there is not winner of the game.
    }
        public boolean MakeMove(char player, int row, int col){
            if(row>=0 && row <=2 && col>=0 && col <= 2){
                if(gameBoard[row][col]!=' ')
                    return false;
                    //returns false if the rows and columns of the board do not equal spaces.

                else {
                    gameBoard[row][col] = player;
                    return true;
                    //returns true if the row and columns can be occupied by the player.
                }
            }
            else return false;
        }// this method above executes when the player makes a move.

    public boolean scored(){
            if(enps_win1 == 0){
             return false;
             //if object enps_win1 equals 0 then return false.
            }
            else{return true;}
            //else return this boolean as true.
    }
    public static void main(String args[]){
        Gameboard thisGameBoard = new Gameboard();
        double AI_check = 0;
        Scanner AI_Scanner = new Scanner(System.in);
        System.out.println("Do you wish to play against a bot?\n1. Yes\n2. No");
        String AI_Answer = AI_Scanner.nextLine();
        if(AI_Answer.equals("1")){
            AI_check = 1;
            //this will check if the user of the program wants to play against a bot.
            // 1 = yes
            System.out.println("You will be playing against a pre-written script");
        }else if(AI_Answer.equals("2")){
            System.out.println("Please enter your name, player 1 (O)? ");
            String localname1 = AI_Scanner.nextLine();
            thisGameBoard.readyplayerone(localname1);

            System.out.println("Please enter your name, player 2 (X)? ");
            String localname2 = AI_Scanner.nextLine();
            thisGameBoard.readyplayertwo(localname2);

            AI_check = 0;
            // 0 = no
        }else{
            System.out.println("Please enter a valid input");
        }
        //Gameboard thisGameBoard = new Gameboard();
        thisGameBoard.displayBoard();
        //makes a new object called thisGameBoard which will be used to jump to different methods.
        //then the board is displayed at the start of the game.
        int counter = 1;
        //makes variable called counter equal to one.

        while(thisGameBoard.gameActive() && counter < 10){
            //while the game is active and the game hasn't had 10 turns execute the next piece section.
         if((counter % 2 == 0)&&(AI_check == 0)) {
             thisGameBoard.keeptrackinitial(counter);
             thisGameBoard.askPlayer('O');
             //this will execute when the counter is equally dividable by 2. So when it is player 0's turn.
         }else if(AI_check == 1)
             thisGameBoard.AI_run('X', thisGameBoard);
            //this will jump to the AI code for the AI to select a position.
         else if(AI_check == 0)
            thisGameBoard.askPlayer('X');
            //otherwise this will execute when it is player X's turn.
         counter++;

            System.out.println("\n");
            //this will be used as a line break in the code.
            thisGameBoard.displayBoard();
            //the above line will be used to jump to the displayBoard method in the code.
            thisGameBoard.checkwin();
            //the above line will be used to jump to the checking method in the code.

            if((counter == 10)&&(thisGameBoard.scored())){
                System.out.print("It's a draw\n");
                //this will execute if the game goes on for 10 turns, at this point the board is full and therefore
                // it is a draw.
            }
        }
    }
    public void AI_run(char Bot, Gameboard currentGameBoard){
        Scanner keyboard = new Scanner(System.in);
        Gameboard jump_from_AI = new Gameboard();
        Random VarRand = new Random();
        int row, col;
        do {
            row = VarRand.nextInt(3)+1;
            //3 is the maximum and the 1 is the minimum

            col = VarRand.nextInt(3)+1;
            //3 is the maximum and the 1 is the minimum
        }while (notValid(row,col));

        MakeMove(Bot,row-1,col-1);
        //currentGameBoard.displayBoard();
        currentGameBoard.checkwin();
        currentGameBoard.askPlayer('O');

        System.out.println("\n");
        //this will be used as a line break in the code.
        currentGameBoard.checkwin();
        //the above line will be used to jump to the checkwin method in the code.
    }

    public void keeptrackinitial(double tokeep){
        keeptrack = tokeep;
    }
    public void readyplayerone(String name1){
        player1name = name1;
    }
    public void readyplayertwo(String name2){
        player2name = name2;
    }
}
