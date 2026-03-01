package iscteiul.ista.battleship;

/**
 * Represents a Frigate ship in the Battleship game.
 * A Frigate has size 4 and can be placed horizontally or vertically.
 */
public class Frigate extends Ship {

    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Creates a Frigate with a given orientation and starting position.
     *
     * @param bearing orientation of the ship (NORTH, SOUTH, EAST, WEST)
     * @param pos starting board position
     * @throws IllegalArgumentException if orientation is invalid
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(NAME, bearing, pos);

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;

            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;

            default:
                throw new IllegalArgumentException("Invalid bearing for Frigate");
        }
    }

    /**
     * Returns the size of the Frigate.
     *
     * @return ship size (4)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
