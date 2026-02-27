package iscteiul.ista.battleship;

/**
 * Representa uma Caravela no jogo da Batalha Naval dos Descobrimentos.
 * A Caravela é um navio de dimensão 2 (ocupa 2 quadrados na grelha) e 
 * cada jogador possui 3 destas embarcações na sua frota.
 * * @author andre
 * @version 1.0
 */
public class Caravel extends Ship {
    
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Construtor da classe Caravel (Caravela).
     * Inicializa a Caravela com o seu nome predefinido ("Caravela"), a sua orientação e a posição inicial.
     * Calcula automaticamente as restantes posições que o navio ocupa na grelha com base na sua orientação.
     * * @param bearing A orientação para onde a Caravela aponta (ex: NORTH, SOUTH, EAST, WEST).
     * @param pos     O ponto inicial (coordenadas) para o posicionamento da Caravela.
     * @throws NullPointerException Se a orientação (bearing) fornecida for nula.
     * @throws IllegalArgumentException Se a orientação (bearing) fornecida não for reconhecida (inválida).
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Obtém a dimensão (tamanho) da Caravela.
     * Este método sobrepõe (override) a implementação da superclasse (Ship).
     * * @return O número de posições que o navio ocupa na grelha (2).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
