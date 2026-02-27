package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fleet of ships in the Battleship game.
 * A fleet is responsible for:
 * <ul>
 *     <li>Storing ships</li>
 *     <li>Validating ship placement</li>
 *     <li>Detecting collisions</li>
 *     <li>Providing ship status information</li>
 * </ul>
 */
public class Fleet implements IFleet {

    private List<IShip> ships;

    /**
     * Creates an empty fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Prints all ships from a given list.
     *
     * @param ships list of ships to print
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    /**
     * Returns all ships currently in the fleet.
     *
     * @return list of ships
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adds a ship to the fleet if:
     * <ul>
     *     <li>The fleet is not full</li>
     *     <li>The ship is inside board boundaries</li>
     *     <li>No collision risk exists</li>
     * </ul>
     *
     * @param s ship to add
     * @return true if the ship was successfully added; false otherwise
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Returns all ships that belong to a specific category.
     *
     * @param category category of interest (e.g. "Galeao", "Fragata")
     * @return list of ships in that category
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);
        return shipsLike;
    }

    /**
     * Returns all ships that are still floating.
     *
     * @return list of ships not yet sunk
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);
        return floatingShips;
    }

    /**
     * Returns the ship occupying a given position.
     *
     * @param pos board position
     * @return ship at that position or null if none exists
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (IShip ship : ships)
            if (ship.occupies(pos))
                return ship;
        return null;
    }

    /**
     * Verifies if a ship is fully inside board limits.
     *
     * @param s ship to validate
     * @return true if inside board boundaries
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 &&
                s.getRightMostPos() <= BOARD_SIZE - 1 &&
                s.getTopMostPos() >= 0 &&
                s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks if placing a ship causes collision
     * or is too close to another ship.
     *
     * @param s ship to validate
     * @return true if collision risk exists
     */
    private boolean colisionRisk(IShip s) {
        for (IShip ship : ships)
            if (ship.tooCloseTo(s))
                return true;
        return false;
    }

    /**
     * Prints full fleet status grouped by category.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints ships of a specific category.
     *
     * @param category category to filter
     */
    public void printShipsByCategory(String category) {
        assert category != null;
        printShips(getShipsLike(category));
    }

    /**
     * Prints ships that are still floating.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all ships in the fleet.
     */
    void printAllShips() {
        printShips(ships);
    }
}
