/**
 * Representa uma Barca no jogo da Batalha Naval dos Descobrimentos.
 * A Barca é o navio mais pequeno do jogo (equivalente ao Submarino tradicional),
 * ocupando apenas 1 quadrado na grelha.
 * * @author andre
 * @version 1.0
 */
public class Barge extends Ship {
    
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Construtor da classe Barge (Barca).
     * Inicializa a Barca com o seu nome predefinido ("Barca"), a sua orientação e a posição inicial.
     * Como tem tamanho 1, a lista de posições ocupadas conterá apenas a posição fornecida.
     * * @param bearing A orientação do navio (ex: Horizontal ou Vertical, definido pela classe Compass).
     * @param pos     A posição de origem (coordenadas da grelha) onde a Barca será colocada.
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Obtém o tamanho da Barca.
     * Este método sobrepõe (override) o método da classe mãe (Ship).
     * * @return O número de posições que o navio ocupa na grelha (1).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
