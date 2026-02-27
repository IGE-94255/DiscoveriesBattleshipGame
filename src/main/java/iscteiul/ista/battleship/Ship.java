/**
 * Classe abstrata que representa um navio no jogo Batalha Naval.
 * <p>
 * Um navio é caracterizado por:
 * <ul>
 *     <li>Uma categoria (tipo de navio)</li>
 *     <li>Uma orientação (bearing)</li>
 *     <li>Uma posição inicial no tabuleiro</li>
 *     <li>Um conjunto de posições que ocupa</li>
 * </ul>
 * </p>
 *
 * As subclasses concretas (por exemplo, Barge, Caravel, Carrack,
 * Frigate e Galleon) definem o tamanho do navio e a forma como
 * as posições são distribuídas no tabuleiro.
 *
 * Implementa a interface {@code IShip}.
 *
 * @author Mariana
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementação base comum a todos os tipos de navios.
 */
public abstract class Ship implements IShip {

    /** Identificador textual do tipo "Galeão" */
    private static final String GALEAO = "galeao";

    /** Identificador textual do tipo "Fragata" */
    private static final String FRAGATA = "fragata";

    /** Identificador textual do tipo "Nau" */
    private static final String NAU = "nau";

    /** Identificador textual do tipo "Caravela" */
    private static final String CARAVELA = "caravela";

    /** Identificador textual do tipo "Barca" */
    private static final String BARCA = "barca";

    /**
     * Método de fábrica responsável por criar instâncias concretas
     * de navios consoante o tipo indicado.
     *
     * @param shipKind tipo de navio (barca, caravela, nau, fragata, galeao)
     * @param bearing  orientação do navio no tabuleiro
     * @param pos      posição inicial do navio
     * @return instância concreta de {@code Ship} ou {@code null}
     *         caso o tipo seja inválido
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** Categoria (tipo) do navio */
    private String category;

    /** Orientação do navio no tabuleiro */
    private Compass bearing;

    /** Posição inicial (âncora) do navio */
    private IPosition pos;

    /** Lista de posições ocupadas pelo navio */
    protected List<IPosition> positions;

    /**
     * Constrói um navio com categoria, orientação e posição inicial.
     *
     * @param category tipo do navio
     * @param bearing  orientação do navio
     * @param pos      posição inicial
     *
     * @throws AssertionError se bearing ou pos forem null
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        this.positions = new ArrayList<>();
    }

    /**
     * Devolve a categoria do navio.
     *
     * @return categoria do navio
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Devolve a lista de posições ocupadas pelo navio.
     *
     * @return lista de posições
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Devolve a posição inicial do navio.
     *
     * @return posição inicial
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Devolve a orientação do navio.
     *
     * @return orientação (bearing)
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda está a flutuar.
     * <p>
     * Um navio está a flutuar se pelo menos uma das suas posições
     * ainda não tiver sido atingida.
     *
     * @return {@code true} se ainda existir pelo menos uma posição não atingida;
     *         {@code false} caso contrário
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Obtém a menor linha ocupada pelo navio.
     *
     * @return valor da linha mais superior
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Obtém a maior linha ocupada pelo navio.
     *
     * @return valor da linha mais inferior
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Obtém a menor coluna ocupada pelo navio.
     *
     * @return valor da coluna mais à esquerda
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Obtém a maior coluna ocupada pelo navio.
     *
     * @return valor da coluna mais à direita
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se o navio ocupa uma determinada posição.
     *
     * @param pos posição a verificar
     * @return {@code true} se o navio ocupar a posição;
     *         {@code false} caso contrário
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo de outro navio.
     * <p>
     * Dois navios são considerados demasiado próximos se alguma das suas
     * posições for adjacente.
     *
     * @param other outro navio
     * @return {@code true} se estiverem demasiado próximos;
     *         {@code false} caso contrário
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se o navio está demasiado próximo de uma posição.
     *
     * @param pos posição a verificar
     * @return {@code true} se alguma posição do navio for adjacente;
     *         {@code false} caso contrário
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Regista um disparo numa determinada posição.
     * <p>
     * Se a posição pertencer ao navio, essa posição é marcada como atingida.
     *
     * @param pos posição alvo do disparo
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Devolve uma representação textual do navio.
     *
     * @return string no formato "[categoria orientação posição]"
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }
}
