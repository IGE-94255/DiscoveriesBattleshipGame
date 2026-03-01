/**
 * Implementação principal do jogo de Batalha Naval.
 * Gere o estado da partida, incluindo os tiros efetuados
 * e as estatísticas associadas.
 *
 * @author Tiago
 * @version 1.0
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Controla a lógica do jogo da Batalha Naval.
 * Responsável por gerir os tiros, estatísticas e interagir com a frota.
 */
public class Game implements IGame {

    /** Frota adversária sobre a qual os tiros são disparados. */
    private IFleet fleet;

    /** Lista de posições onde foram efetuados tiros válidos e não repetidos. */
    private List<IPosition> shots;

    /** Contador de tiros disparados para posições fora do tabuleiro. */
    private Integer countInvalidShots;

    /** Contador de tiros disparados para posições já anteriormente atingidas. */
    private Integer countRepeatedShots;

    /** Contador de tiros que acertaram num navio. */
    private Integer countHits;

    /** Contador de navios completamente afundados. */
    private Integer countSinks;

    /**
     * Cria um novo jogo com a frota fornecida.
     * Inicializa todos os contadores a zero e a lista de tiros vazia.
     *
     * @param fleet a frota adversária a ser usada no jogo
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
     * Dispara um tiro numa posição do tabuleiro adversário.
     * O tiro é ignorado se for inválido ou repetido, sendo contabilizado
     * no respetivo contador. Se afundar um navio, devolve esse navio.
     *
     * @param pos a posição onde o tiro é disparado
     * @return o navio afundado se o tiro o tiver afundado, {@code null} caso contrário
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

    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    @Override
    public int getRepeatedShots() {
        return countRepeatedShots;
    }

    @Override
    public int getInvalidShots() {
        return countInvalidShots;
    }

    @Override
    public int getHits() {
        return countHits;
    }

    @Override
    public int getSunkShips() {
        return countSinks;
    }

    @Override
    public int getRemainingShips() {
        return fleet.getFloatingShips().size();
    }

    /**
     * Verifica se a posição fornecida é válida, ou seja,
     * se está dentro dos limites do tabuleiro.
     *
     * @param pos a posição a verificar
     * @return {@code true} se a posição for válida, {@code false} caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() < Fleet.BOARD_SIZE
                && pos.getColumn() >= 0 && pos.getColumn() < Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se a posição fornecida já foi alvo de um tiro anteriormente.
     *
     * @param pos a posição a verificar
     * @return {@code true} se o tiro for repetido, {@code false} caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        return shots.contains(pos);
    }

    /**
     * Imprime no ecrã um tabuleiro 10x10 marcando as posições fornecidas
     * com o caráter indicado. As restantes posições são marcadas com '.'.
     *
     * @param positions lista de posições a marcar no tabuleiro
     * @param marker    caráter usado para marcar as posições
     */
    public void printBoard(List<IPosition> positions, Character marker) {
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
     * Imprime no ecrã o tabuleiro com os tiros válidos efetuados,
     * marcados com o caráter 'X'.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime no ecrã o tabuleiro com as posições ocupadas pela frota,
     * marcadas com o caráter '#'.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<>();
        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());
        printBoard(shipPositions, '#');
    }
}