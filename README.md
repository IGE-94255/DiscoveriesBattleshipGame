# Battleship

Basic academic version of Battleship game to build upon.

## Tipos de Navios – Discoveries Battleship Game

| Batalha Naval         | Descobrimentos | English   | Dimensão | #Navios |
|-----------------------|---------------|-----------|----------|---------|
| Porta-aviões          | Galeão        | Galleon   | 5        | 1       |
| Navio de 4 canhões    | Fragata       | Frigate   | 4        | 1       |
| Navio de 3 canhões    | Nau           | Carrack   | 3        | 2       |
| Navio de 2 canhões    | Caravela      | Caravel   | 2        | 3       |
| Submarino             | Barca         | Barge     | 1        | 4       |

## Regras de Jogo:

É jogado num tabuleiro (tipicamente 10x10). O sistema de coordenadas é definido por:

      (x,y) ∈ 0, ..., 9 x 0, ..., 9

O impacto dos ataques é calculado pela interseção do vetor do ataque com a hitbox do navio.
