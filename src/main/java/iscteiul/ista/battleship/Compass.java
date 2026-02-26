package iscteiul.ista.battleship;

/**
 * Representa as orientações (pontos cardeais) possíveis para os navios 
 * na grelha do jogo da Batalha Naval dos Descobrimentos.
 * Inclui as direções Norte, Sul, Este e Oeste (representada por 'o'), 
 * bem como um estado para orientações desconhecidas.
 * * @author andre
 * @version 1.0
 */
public enum Compass {
    
    /** Orientação Norte (representada pelo caractere 'n') */
    NORTH('n'), 
    
    /** Orientação Sul (representada pelo caractere 's') */
    SOUTH('s'), 
    
    /** Orientação Este/Leste (representada pelo caractere 'e') */
    EAST('e'), 
    
    /** Orientação Oeste (representada pelo caractere 'o') */
    WEST('o'), 
    
    /** Orientação Desconhecida ou inválida (representada pelo caractere 'u') */
    UNKNOWN('u');

    /** Caractere interno que representa a direção */
    private final char c;

    /**
     * Construtor do enumerado Compass.
     * Associa um caractere representativo a cada direção cardeal.
     * * @param c O caractere correspondente à direção (ex: 'n', 's', 'e', 'o').
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o caractere que representa esta direção.
     * * @return O caractere representativo da direção.
     */
    public char getDirection() {
        return c;
    }

    /**
     * Devolve a representação em texto (String) da direção.
     * Este método sobrepõe (override) a implementação padrão.
     * * @return Uma String contendo apenas o caractere da direção.
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um caractere na respetiva direção (Compass) do enumerado.
     * Útil para interpretar comandos do utilizador ou ficheiros de configuração.
     * * @param ch O caractere a ser convertido ('n', 's', 'e', 'o').
     * @return O valor de Compass correspondente ao caractere fornecido. 
     * Retorna UNKNOWN se o caractere não for reconhecido.
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
