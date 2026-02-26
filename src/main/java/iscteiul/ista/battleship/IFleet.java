/**
 * Interface que define as operações principais de uma frota de navios
 * no jogo de Batalha Naval.
 * Inclui operações de gestão dos navios e consulta do estado da frota.
 *
 * @author Tiago
 * @version 1.0
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IFleet {

    /** Dimensão do tabuleiro de jogo (10x10). */
    Integer BOARD_SIZE = 10;

    /** Número total de navios que compõem a frota. */
    Integer FLEET_SIZE = 10;

    /**
     * Devolve a lista de todos os navios da frota.
     *
     * @return lista com todos os navios da frota
     */
    List<IShip> getShips();

    /**
     * Adiciona um navio à frota, caso a sua posição seja válida
     * e não entre em conflito com navios já existentes.
     *
     * @param s o navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso,
     *         {@code false} caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Devolve a lista de navios da frota que pertencem à categoria indicada.
     *
     * @param category a categoria dos navios a pesquisar (ex: "Galleon", "Frigate")
     * @return lista de navios da categoria indicada, vazia se não existirem
     */
    List<IShip> getShipsLike(String category);

    /**
     * Devolve a lista de navios da frota que ainda não foram afundados.
     *
     * @return lista de navios ainda a flutuar
     */
    List<IShip> getFloatingShips();

    /**
     * Devolve o navio que ocupa a posição indicada no tabuleiro.
     *
     * @param pos a posição a verificar
     * @return o navio que ocupa a posição, ou {@code null} se a posição estiver vazia
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime no ecrã o estado atual da frota, indicando para cada navio
     * se ainda está a flutuar ou se foi afundado.
     */
    void printStatus();
}
