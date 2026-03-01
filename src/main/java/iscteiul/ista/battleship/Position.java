/**
 * Representa uma posição no tabuleiro do jogo Batalha Naval.
 * <p>
 * Uma posição é definida por uma linha e uma coluna, podendo estar:
 * <ul>
 *     <li>Ocupada por parte de um navio</li>
 *     <li>Já atingida por um disparo</li>
 * </ul>
 * </p>
 *
 * Implementa a interface {@code IPosition}.
 *
 * @author Mariana
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Implementação concreta de uma posição no tabuleiro.
 */
public class Position implements IPosition {

    /** Número da linha da posição no tabuleiro */
    private int row;

    /** Número da coluna da posição no tabuleiro */
    private int column;

    /** Indica se a posição está ocupada por um navio */
    private boolean isOccupied;

    /** Indica se a posição já foi atingida por um disparo */
    private boolean isHit;

    /**
     * Constrói uma nova posição com a linha e coluna especificadas.
     * A posição é inicialmente criada como não ocupada e não atingida.
     *
     * @param row    número da linha
     * @param column número da coluna
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Devolve a linha da posição.
     *
     * @return número da linha
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Devolve a coluna da posição.
     *
     * @return número da coluna
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gera o código hash da posição.
     *
     * @return valor de hash baseado na linha, coluna, estado de ocupação e estado de impacto
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto.
     * <p>
     * Duas posições são consideradas iguais se tiverem a mesma linha e coluna,
     * independentemente do estado (ocupada ou atingida).
     *
     * @param otherPosition objeto a comparar
     * @return {@code true} se tiverem a mesma linha e coluna; {@code false} caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;

        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() &&
                    this.getColumn() == other.getColumn());
        }
        return false;
    }

    /**
     * Verifica se esta posição é adjacente a outra.
     * <p>
     * Considera-se adjacente qualquer posição cuja diferença de linha e coluna
     * seja no máximo 1 (incluindo diagonais).
     *
     * @param other posição a verificar
     * @return {@code true} se for adjacente; {@code false} caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 &&
                Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca a posição como ocupada por um navio.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Marca a posição como atingida por um disparo.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Indica se a posição está ocupada.
     *
     * @return {@code true} se estiver ocupada; {@code false} caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Indica se a posição já foi atingida.
     *
     * @return {@code true} se já foi alvo de disparo; {@code false} caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação textual da posição.
     *
     * @return string no formato "Linha = X Coluna = Y"
     */
    @Override
    public String toString() {
        return "Linha = " + row + " Coluna = " + column;
    }
}
