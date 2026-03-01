/**
 * Interface que define as operações principais de um jogo de Batalha Naval.
 * Inclui operações de disparo, consulta de estatísticas e visualização do estado atual da partida.
 *
 * @author Tiago
 * @version 1.0
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IGame {

    /**
     * Dispara um tiro numa posição do tabuleiro adversário.
     *
     * @param pos a posição onde o tiro é disparado
     * @return o navio atingido se houver acerto, {@code null} se for água,
     *         tiro repetido ou posição inválida
     */
    IShip fire(IPosition pos);

    /**
     * Devolve a lista de todas as posições onde foram disparados tiros válidos.
     *
     * @return lista de posições dos tiros válidos
     */
    List<IPosition> getShots();

    /**
     * Devolve o número de tiros repetidos efetuados durante o jogo.
     *
     * @return número de tiros repetidos
     */
    int getRepeatedShots();

    /**
     * Devolve o número de tiros inválidos efetuados durante o jogo.
     *
     * @return número de tiros inválidos
     */
    int getInvalidShots();

    /**
     * Devolve o número total de tiros que acertaram num navio adversário.
     *
     * @return número de acertos
     */
    int getHits();

    /**
     * Devolve o número de navios adversários completamente afundados.
     *
     * @return número de navios afundados
     */
    int getSunkShips();

    /**
     * Devolve o número de navios adversários ainda não afundados.
     * Quando este valor chega a zero, o jogador venceu o jogo.
     *
     * @return número de navios restantes
     */
    int getRemainingShips();

    /**
     * Imprime no ecrã todas as posições onde foram efetuados tiros válidos,
     * indicando se cada tiro resultou em acerto ou água.
     */
    void printValidShots();

    /**
     * Imprime no ecrã o estado atual da frota adversária,
     * identificando quais os navios afundados e quais os que estão ativos.
     */
    void printFleet();
}
