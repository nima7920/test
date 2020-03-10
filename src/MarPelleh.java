import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MarPelleh {
    public static void main(String[] args) {
        //create a Random object by a seed read from console
        Scanner scanner = new Scanner(System.in);
        //Create a marPelleh with 2 players and get those players
        Board board = new Board(2);
        Player player1 = board.getPlayers().get(0);
        Player player2 = board.getPlayers().get(1);

        //movements and printing results
        for (int i = 0; i < 25; i++) {
            board.movePlayer(0, scanner.nextInt(), scanner.nextInt());
            board.movePlayer(1, scanner.nextInt(), scanner.nextInt());

            System.out.println("Player1 is in " + player1.getCurrentCell());
            System.out.println("Player2 is in " + player2.getCurrentCell());
            System.out.println("Total score of player1 is " + player1.getTotalScore());
            System.out.println("Total score of player2 is " + player2.getTotalScore());
            System.out.println("ScoreFromLadder_Snake of player1 is " +
                    player1.getScoreFromLadder_Snake());
            System.out.println("ScoreFromLadder_Snake of player2 is " +
                    player2.getScoreFromLadder_Snake());
            System.out.println("Player1 is better than player2 : " + P1greaterThanP2(player1, player2));
        }
    }

    public static boolean P1greaterThanP2(Player player1, Player player2) {
        int result = player1.compareTo(player2);
        if (result > 0)
            return true;
        else
            return false;
    }
}

class Board {
    private Cell[][] cells;
    private ArrayList<Player> players;
    private ArrayList<Transmitter> transmitters;

    public Board(int numberOfPlayers) {
        initCells();
        initTransmitters();
        updateTransmittersOfCells();
        initPlayers(numberOfPlayers);
    }

    //initialize methods
    private void initCells() {
        cells = new Cell[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                cells[i][j] = new Cell(i, j);
    }

    private void initTransmitters() {
        /*
        Notice that you must use cells of board class and you
        can't create new cell in your code.You can only use methods there
        exist.

        Create five ladders here and add them to "transmitters" list
        1.A badLadder from (0,3) to (5,6)
        2.A badLadder from (3,4) to (7,8)
        3.A badLadder from (5,8) to (9,4)
        4.A goodLadder from (0,9) to (6,9)
        5.A goodLadder from (4,6) to (8,5)

        Also create five snakes here and add them to "transmitters" list
        1.A badSnake from (7,3) to (0,4)
        2.A badSnake from (8,8) to (5,5)
        3.A goodSnake from (9,3) to (1,1)
        4.A goodSnake from (4,4) to (2,9)
        5.A goodSnake from (8,6) to (5,7)
         */
        transmitters = new ArrayList<>();
        Transmitter bl1=new BadLadder();
        bl1.setFirstCell(getCell(0,3)); bl1.setLastCell(getCell(5,6));
        transmitters.add(bl1);

        Transmitter bl2=new BadLadder();
        bl2.setFirstCell(getCell(3,4)); bl2.setLastCell(getCell(7,8));
        transmitters.add(bl2);

        Transmitter bl3=new BadLadder();
        bl3.setFirstCell(getCell(5,8)); bl3.setLastCell(getCell(9,4));
        transmitters.add(bl3);

        Transmitter gl1=new GoodLadder();
        gl1.setFirstCell(getCell(0,9)); gl1.setLastCell(getCell(6,9));
        transmitters.add(gl1);

        Transmitter gl2=new GoodLadder();
        gl2.setFirstCell(getCell(4,6)); gl2.setLastCell(getCell(8,5));
        transmitters.add(gl2);

        Transmitter bs1=new BadSnake();
        bs1.setFirstCell(getCell(7,3));bs1.setLastCell(getCell(0,4));
        transmitters.add(bs1);

        Transmitter bs2=new BadSnake();
        bs2.setFirstCell(getCell(8,8));bs2.setLastCell(getCell(5,5));
        transmitters.add(bs2);

        Transmitter gs1=new GoodSnake();
        gs1.setFirstCell(getCell(9,3));gs1.setLastCell(getCell(1,1));
        transmitters.add(gs1);

        Transmitter gs2=new GoodSnake();
        gs2.setFirstCell(getCell(4,4));gs2.setLastCell(getCell(2,9));
        transmitters.add(gs2);

        Transmitter gs3=new GoodSnake();
        gs3.setFirstCell(getCell(8,6));gs3.setLastCell(getCell(5,7));
        transmitters.add(gs3);
        //YOUR CODE HERE
    }

    private void updateTransmittersOfCells() {
        for (Transmitter transmitter : transmitters)
            transmitter.getFirstCell().setTransmitter(transmitter);
    }

    private void initPlayers(int numberOfPlayers) {
        players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++)
            players.add(new Player(getCell(0, 0)));
    }

    //normal methods
    public void movePlayer(int indexOfPlayer, int newX, int newY) {
        Player currentPlayer = getPlayers().get(indexOfPlayer);
        Cell newCell = getCell(newX, newY);
        currentPlayer.setCurrentCell(newCell);
        checkIfMustTransmit(currentPlayer);
    }

    private void checkIfMustTransmit(Player player) {
        Transmitter transmitter = player.getCurrentCell().getTransmitter();
        if (transmitter != null) {
            transmitter.transmit(player);
        }
    }

    //getter, setter methods
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}

class Cell {
    private int x, y;
    private Transmitter transmitter;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //setter, getter methods
    public void setTransmitter(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    public Transmitter getTransmitter() {
        return transmitter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Override methods
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}

class Player implements Comparable<Player> {
    private Cell currentCell;
    private int scoreFromLadder_Snake = 0;

    public Player(Cell currentCell) {
        this.currentCell = currentCell;
    }

    //getter, setter methods
    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public void setScoreFromLadder_Snake(int scoreFromLadder_Snake) {
        this.scoreFromLadder_Snake = scoreFromLadder_Snake;
    }

    public int getScoreFromLadder_Snake() {
        return scoreFromLadder_Snake;
    }

    public int getTotalScore() {
        return getCurrentCell().getX() + getCurrentCell().getY()
                + getScoreFromLadder_Snake();
    }

    @Override
    public int compareTo(Player o) {
        if(this.getTotalScore()>o.getTotalScore())
            return 1;
        else if(this.getTotalScore()<o.getTotalScore())
            return -1;
        else
            return 0;
    }
}

abstract class Transmitter {
    //YOUR CODE HERE
    private Cell firstCell,lastCell;
    public abstract void transmit(Player player);

    public Cell getFirstCell() {
        //YOUR CODE HERE
        return firstCell;
    }
    public void setFirstCell(Cell cell){
        this.firstCell=cell;
    }
    public Cell getLastCell(){
        return this.lastCell;
    }
    public void setLastCell(Cell cell){
        this.lastCell=cell;
    }
}

abstract class Snake extends Transmitter {
    //YOUR CODE HERE
    public static int badSnakeTransmits=0,goodSnakeTransmits=0;
}

class BadSnake extends Snake {
    @Override
    public void transmit(Player player) {
        // checking whether score decrease is possible
        if(badSnakeTransmits<7)
            player.setScoreFromLadder_Snake(player.getScoreFromLadder_Snake()-1);
        player.setCurrentCell(this.getLastCell());
        badSnakeTransmits++;
    }
    //YOUR CODE HERE
}

class GoodSnake extends Snake {
    @Override
    public void transmit(Player player) {
        // checking whether transmission is possible
        if(goodSnakeTransmits<badSnakeTransmits){
            player.setCurrentCell(this.getLastCell());
            goodSnakeTransmits++;
        }
    }
    //YOUR CODE HERE
}

abstract class Ladder extends Transmitter {
    //YOUR CODE HERE
    public static int badLadderTransmits=0,goodLadderTransmits=0;
}
class BadLadder extends Ladder {
    @Override
    public void transmit(Player player) {
        // checking whether transmission is possible
        if(Snake.badSnakeTransmits+Snake.goodSnakeTransmits>Ladder.badLadderTransmits+Ladder.goodLadderTransmits){
            player.setCurrentCell(this.getLastCell());
            badLadderTransmits++;
        }
    }
    //YOUR CODE HERE
}
class GoodLadder extends Ladder {
    @Override
    public void transmit(Player player) {
        // checking whether transmission is possible
        if(Snake.badSnakeTransmits+Snake.goodSnakeTransmits>Ladder.badLadderTransmits+Ladder.goodLadderTransmits){
            player.setCurrentCell(this.getLastCell());
            if(goodLadderTransmits<6){
                player.setScoreFromLadder_Snake(player.getScoreFromLadder_Snake()+1);
            }
            goodLadderTransmits++;

        }
    }
    //YOUR CODE HERE
}

