package iscteiul.ista.battleship;

/**
 * Representa uma Nau no jogo da Batalha Naval dos Descobrimentos.
 * A Nau é um navio de dimensão 3 (equivalente ao navio de 3 canhões tradicional)
 * e cada jogador possui 2 destas embarcações na sua frota.
 * * @author aaa
 * @version 1.0
 */
public class Carrack extends Ship {
    
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Construtor da classe Carrack (Nau).
     * Inicializa a Nau com o seu nome predefinido ("Nau"), a sua orientação e a posição de origem.
     * As posições subsequentes que a Nau ocupa na grelha são calculadas e adicionadas 
     * à lista de posições com base na orientação fornecida.
     * * @param bearing A orientação do navio (ex: NORTH, SOUTH, EAST, WEST).
     * @param pos     O ponto inicial (coordenadas da grelha) para o posicionamento da Nau.
     * @throws IllegalArgumentException Se a orientação (bearing) fornecida não for válida ou não for reconhecida.
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
        
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Obtém a dimensão (tamanho) da Nau.
     * Este método sobrepõe (override) a implementação da superclasse (Ship).
     * * @return O número de posições que o navio ocupa na grelha (3).
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
