package org.kata.hole6;

public class Game {
    private char lastPlayer = ' ';
    private Board board = new Board();

    public char Winner() {
        return board.rowHasSameSymbolOnAllColumns();
    }

    public void play(char player, int x, int y) throws Exception {
        validateFirstMove(player);
        validatePlayer(player);
        validatePosition(x, y);

        updatePlayer(player);
        updateBoard(player, x, y);
    }

    private void validateFirstMove(char player) throws Exception {
        if (lastPlayer == ' ' && player == 'O') {
            throw new Exception("Invalid first player");
        }
    }

    private void validatePlayer(char player) throws Exception {
        if (player == lastPlayer) {
            throw new Exception("Invalid next player");
        }
    }

    private void validatePosition(int x, int y) throws Exception {
        if (board.TileAt(x, y).Symbol != ' ') {
            throw new Exception("Invalid position");
        }
    }
    private void updatePlayer(char player) {
        lastPlayer = player;
    }

    private void updateBoard(char player, int x, int y) {
        board.AddTileAt(player, x, y);
    }
}