
import java.util.Arrays;
import java.util.Scanner;

public class MyTicTacToe {
    private static final Scanner input = new Scanner(System.in);
    private static final char FIRST_PLAYERS_SYMBOL = 'X';
    private static final char SECOND_PLAYERS_SYMBOL = 'Y';
    private static final char EMPTY_BOX = ' ';
    private final char[][] gameBoard = new char[3][3];
    private String playerOne;
    private String playerTow;
    private String currentPlayer;
    private String whoWonTheGame;

    public void gameStart(){
        initializeGameBoard();
        askForUserName();
        while (isGameNotOver()){
            drawBoard();
            printPlayerTurn();
            askForManeuver();
        }
        printGameOver();
    }
    private void initializeGameBoard(){
        for (char[] chars: gameBoard) {
            Arrays.fill(chars, EMPTY_BOX);
        }
    }
    // board drawing
    // set a username for players
    private void askForUserName(){
        System.out.println("--------> Welcome to my game <--------");
        System.out.println("Enter your name : ");
        playerOne = input.nextLine();
        System.out.println("Enter a player name who will play with you : ");
        playerTow = input.nextLine();

        System.out.println();
        System.out.println("Who will play first? Enter \n1 for " + playerOne + "\nAnd press 2 for " + playerTow );
        int choice = input.nextInt();
        currentPlayer = (choice == 1) ? playerOne : playerTow;
    }
    private boolean isGameNotOver(){
        return !(isBoardFull() || hasAnyWinner());
    }
    private void drawBoard(){
        System.out.println("|---|---|---|");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %c | %c | %c |\n", gameBoard[i][0], gameBoard[i][1], gameBoard[i][2]);
            System.out.println("|---|---|---|");
        }
    }
    private void printPlayerTurn(){
        System.out.println(currentPlayer + "'s turn : ");
    }
    private void askForManeuver(){
        int row;
        int col;

        do {
            System.out.print("Enter row number(0, 1, or 2) : " );
            row = input.nextInt();
            System.out.print("Enter col number(0, 1, or 2) : ");
            col = input.nextInt();
        }while (!inputValidate(row, col));
        if (whoIsPlaying().equals(playerOne)){
            gameBoard[row][col] = FIRST_PLAYERS_SYMBOL;
            currentPlayer = playerTow;
        }else {
            gameBoard[row][col] = SECOND_PLAYERS_SYMBOL;
            currentPlayer = playerOne;
        }
    }
    private String whoIsPlaying(){
        return currentPlayer;
    }
    private void printGameOver(){
        drawBoard();
        System.out.println("\uD83C\uDFAE Game is Over! \uD83C\uDFAE");
        if (whoWonTheGame != null){
            System.out.println(whoWonTheGame + " won the game, " + "Congratulation \uD83C\uD83C \uD83C\uDF89");
        }else {
            System.out.println("Sounds like it's a tie! Play again.");
        }
    }
    private boolean hasAnyWinner(){
        char cross = 0;
        // row checking
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][0] != EMPTY_BOX){
                cross = gameBoard[i][0];
            }
        }
        //row checking
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[0][i] != EMPTY_BOX){
                cross = gameBoard[0][i];
            }
        }

        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != EMPTY_BOX){
            cross = gameBoard[0][0];
        }
        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2] && gameBoard[2][0] != EMPTY_BOX){
            cross = gameBoard[2][0];
        }
        if (cross == FIRST_PLAYERS_SYMBOL){
            whoWonTheGame = playerOne;
        }else if (cross == SECOND_PLAYERS_SYMBOL){
            whoWonTheGame = playerTow;
        }
        return whoWonTheGame != null;
    }
    private boolean isBoardFull(){
        boolean statement = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == EMPTY_BOX){
                    statement = false;
                    break;
                }
            }
            if (!statement) break;
        }
        return statement;
    }
    private boolean inputValidate(int row, int col){
        boolean condition = false;
        if (row < 0 || col < 0 || row > 2 || col > 2){
            System.out.println("Your input is out of bound.");
        }else if (gameBoard[row][col] != EMPTY_BOX){
            System.out.println("Sorry, there is already have one.");
        }else {
            condition = true;
        }
        return condition;
    }
}
