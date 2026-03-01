package iscteiul.ista.battleship;

/**
 * Represents a Galleon ship in the Battleship game.
 * A Galleon has size 5 and a specific shape depending on orientation.
 * The occupied positions are calculated according to its bearing.
 */
public class Galleon extends Ship {

    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Creates a Galleon with a given orientation and starting position.
     *
     * @param bearing orientation of the ship (NORTH, SOUTH, EAST, WEST)
     * @param pos starting board position
     * @throws NullPointerException if bearing is null
     * @throws IllegalArgumentException if bearing is invalid
     */
    public Galleon(Compass bearing, IPosition pos) {
        super(NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("Invalid bearing for Galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;
            default:
                throw new IllegalArgumentException("Invalid bearing for Galleon");
        }
    }

    /**
     * Returns the size of the Galleon.
     *
     * @return ship size (5)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

    /**
     * Fills positions when the ship is facing NORTH.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Fills positions when the ship is facing SOUTH.
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Fills positions when the ship is facing EAST.
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Fills positions when the ship is facing WEST.
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
}
