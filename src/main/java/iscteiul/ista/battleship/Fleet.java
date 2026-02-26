/**
 * Representa a frota de navios de um jogador.
 * Contém métodos para adicionar navios, verificar colisões e imprimir o estado da frota.
 */
public class Fleet implements IFleet {

    private List<IShip> ships;

    /** Cria uma frota vazia. */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /** Retorna todos os navios da frota. */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um navio à frota se não houver risco de colisão e se estiver dentro do tabuleiro.
     *
     * @param s navio a adicionar
     * @return true se o navio foi adicionado com sucesso
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Retorna todos os navios de uma categoria específica.
     *
     * @param category categoria de interesse (ex: "Galeao")
     * @return lista de navios da categoria
     */
    @Override
    public List<IShip> getShipsLike(String category) { ... }

    /** Retorna todos os navios ainda não afundados. */
    @Override
    public List<IShip> getFloatingShips() { ... }

    /**
     * Retorna o navio localizado na posição especificada.
     *
     * @param pos posição no tabuleiro
     * @return navio encontrado ou null
     */
    @Override
    public IShip shipAt(IPosition pos) { ... }

    /** Mostra o estado completo da frota. */
    public void printStatus() { ... }

    /** Imprime todos os navios de uma categoria específica. */
    public void printShipsByCategory(String category) { ... }

    /** Imprime todos os navios ainda não atingidos. */
    public void printFloatingShips() { ... }

    /** Imprime todos os navios da frota. */
    void printAllShips() { ... }

    /** Operação auxiliar que imprime os navios fornecidos. */
    static void printShips(List<IShip> ships) { ... }
}
