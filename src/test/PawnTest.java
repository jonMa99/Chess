package test;

import main.model.Board;
import main.model.Position;
import main.model.pieces.ChessPiece;
import main.model.pieces.Pawn;
import main.model.pieces.PieceName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    private ChessPiece pawn;

    @BeforeEach
    public void setUp() {
        Board board = new Board();
        board.makeBoard();
        pawn = new Pawn(1, 0, 1, board);
    }

    @Test
    public void constructorTest() {
        assertEquals(1, pawn.getPosition().getXcoord());
        assertEquals(0, pawn.getPosition().getYcoord());
        assertEquals(1, pawn.getTeam());
        assertEquals(PieceName.PAWN, pawn.getPieceID());
    }

    @Test
    public void updatePossibleMovesTestBlack() {
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(new Position(1, 1), moves.get(0));
        assertEquals(1, moves.size());
    }

    @Test
    public void updatePossibleMovesTestWhiteWithDoubleMovement() {
        Board board = new Board();
        board.makeBoard();
        pawn = new Pawn(2, 6, 0, board);
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(new Position(2, 4), moves.get(0));
        assertEquals(new Position(2, 5), moves.get(1));
        assertEquals(2, moves.size());
    }

    @Test
    public void updatePossibleMovesTestEdge() {
        pawn.setPosition(new Position(6, 7));
        pawn.updatePossibleMoves();
        List<Position> moves = pawn.getPossibleMoves();
        assertEquals(0, moves.size());
    }
}
