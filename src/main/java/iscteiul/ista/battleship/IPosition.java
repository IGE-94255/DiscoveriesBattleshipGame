/**
 * Interface que define as operações de uma posição no tabuleiro
 * do jogo de Batalha Naval.
 * Cada posição é identificada por uma linha e uma coluna,
 * podendo estar ocupada por um navio e/ou ter sido alvo de um tiro.
 *
 * @author Tiago
 * @version 1.0
 */
package iscteiul.ista.battleship;

public interface IPosition {

    /**
     * Devolve o índice da linha desta posição no tabuleiro.
     *
     * @return índice da linha, entre 0 e {@code BOARD_SIZE - 1}
     */
    int getRow();

    /**
     * Devolve o índice da coluna desta posição no tabuleiro.
     *
     * @return índice da coluna, entre 0 e {@code BOARD_SIZE - 1}
     */
    int getColumn();

    /**
     * Verifica se esta posição é igual a outra, comparando
     * os valores de linha e coluna.
     *
     * @param other o objeto a comparar
     * @return {@code true} se as posições forem iguais, {@code false} caso contrário
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente à posição indicada,
     * ou seja, se estão lado a lado horizontal ou verticalmente.
     *
     * @param other a posição a comparar
     * @return {@code true} se as posições forem adjacentes, {@code false} caso contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca esta posição como ocupada por um navio.
     */
    void occupy();

    /**
     * Marca esta posição como alvo de um tiro.
     */
    void shoot();

    /**
     * Verifica se esta posição está ocupada por um navio.
     *
     * @return {@code true} se a posição estiver ocupada, {@code false} caso contrário
     */
    boolean isOccupied();

    /**
     * Verifica se esta posição já foi alvo de um tiro.
     *
     * @return {@code true} se a posição tiver sido atingida, {@code false} caso contrário
     */
    boolean isHit();
}
