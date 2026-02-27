package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the Battleship game logic.
 * Responsible for handling shots, tracking statistics,
 * and interacting with the fleet.
 */
public class Game implements IGame {

    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Creates a new game using the given fleet.
     *
     * @param fleet fleet used in the game
     */
    public Game(IFleet fleet) {
        this.fleet = fleet;
        this.shots = new ArrayList<>();
        this.countInvalidShots = 0;
        this.countRepeatedShots = 0;
        this.countHits = 0;
        this.countSinks = 0;
    }

    /**
     * Fires a shot at the given position.
     * Updates statistics depending on shot validity and result.
     *
     * @param pos position to fire at
     * @return the ship if it was sunk as a result of the shot; null otherwise
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else {
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns all valid shots performed in the game.
     *
     * @return list of shot positions
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Returns the number of repeated shots.
     */
    @Override
    public int getRepeatedShots() {
        return countRepeatedShots;
    }

    /**
     * Returns the number of invalid shots.
     */
    @Override
    public int getInvalidShots() {
        return countInvalidShots;
    }

    /**
     * Returns the number of successful hits.
     */
    @Override
    public int getHits() {
        return countHits;
    }

    /**
     * Returns the number of sunk ships.
     */
    @Override
    public int getSunkShips() {
        return countSinks;
    }

    /**
     * Returns the number of ships still floating.
     */
    @Override
    public int getRemainingShips() {
        return fleet.getFloatingShips().size();
    }

    /**
     * Validates if a shot is inside board boundaries.
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 &&
                pos.getRow() < Fleet.BOARD_SIZE &&
                pos.getColumn() >= 0 &&
                pos.getColumn() < Fleet.BOARD_SIZE);
    }

    /**
     * Checks whether a shot was already performed.
     */
    private boolean repeatedShot(IPosition pos) {
        return shots.contains(pos);
    }

    /**
     * Prints a board using a specific marker.
     */
    private void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Prints the board showing valid shots.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Prints the board showing fleet positions.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}
