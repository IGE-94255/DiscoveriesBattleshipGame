/**
 * Representa a Fragata, navio de 4 canhões.
 * Define a posição do navio de acordo com a orientação fornecida.
 */
public class Frigate extends Ship {

    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Cria uma Fragata na posição e orientação especificadas.
     *
     * @param bearing orientação do navio (NORTH, SOUTH, EAST, WEST)
     * @param pos posição inicial do navio
     * @throws IllegalArgumentException se a orientação for inválida
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException { ... }

    /** Retorna o tamanho da Fragata. */
    @Override
    public Integer getSize() { return Frigate.SIZE; }
}
