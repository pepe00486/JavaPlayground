package org.pepe.games.tictactoe

import org.pepe.games.common.GameState
import org.pepe.games.common.InputHandler
import org.pepe.games.common.InputValidator
import org.pepe.games.common.Player
import org.pepe.games.common.PlayersPool
import org.pepe.theory.games.common.*
import spock.lang.Specification

class TicTacToeSpec extends Specification {

    public static final PlayersPool playersPool = new PlayersPool(new Player[]{new Player("Mateusz", "x")})

    def 'gameLoop'() {
        given:
        GameState state = new GameState()
        org.pepe.games.common.board.Board board = new org.pepe.games.common.board.Board(3)
        ByteArrayInputStream inp = new ByteArrayInputStream("exit".getBytes());
        System.setIn(inp)
        InputHandler inputHandler = new InputHandler(state, Mock(InputValidator), new Scanner(System.in))
        TicTacToe tikTakToe = new TicTacToe(inputHandler, state, board, playersPool)

        when:
        tikTakToe.gameLoop()
        then:
        state.state == GameState.State.DONE
    }

    def 'gameLoop last'() {
        given:
        GameState state = new GameState()
        org.pepe.games.common.board.Board board = new org.pepe.games.common.board.Board(3)
        board.initializeBoard("#")
        board.playField(new org.pepe.games.common.board.BoardGameInput(0,1,"x") )
        board.playField(new org.pepe.games.common.board.BoardGameInput(0,2,"x") )
        ByteArrayInputStream inp = new ByteArrayInputStream("00".getBytes());
        System.setIn(inp)
        def validator = Mock(InputValidator)
        validator.validateInput("00") >> true
        InputHandler inputHandler = new InputHandler(state, validator, new Scanner(System.in))
        TicTacToe tikTakToe = new TicTacToe(inputHandler, state, board, playersPool)

        when:
        tikTakToe.gameLoop()
        then:
        state.state == GameState.State.DONE
    }

    def "playGameWithInput"() {
        given:
        GameState state = new GameState()
        state.start()

        org.pepe.games.common.board.Board board = Mock()
        board.playField(_ as org.pepe.games.common.board.BoardGameInput) >> valid

        when:
        def result = new TicTacToe(null, state, board, playersPool).playGameWithInput("00", playersPool.current())
        then:

        result.column() == column
        result.row() == row
        state.state == resultState
        where:
        row | column | valid | resultState
        0   | 0      | true  | GameState.State.ONGOING
        0   | 0      | false | GameState.State.INVALID
    }

    def 'State evaluation'() {
        given:
        GameState state = new GameState()
        state.start()

        org.pepe.games.common.board.Board board = Mock()
        board.getSize() >> 3
        board.hasNoSpaceLeft() >> hasNoSpace
        /*
            x x x
            _ x x
            x _ X
         */
        board.getFieldValue(0, 0) >> 'x'
        board.getFieldValue(0, 1) >> 'x'
        board.getFieldValue(0, 2) >> 'x'
        board.getFieldValue(1, 0) >> '_'
        board.getFieldValue(1, 1) >> 'x'
        board.getFieldValue(1, 2) >> 'x'
        board.getFieldValue(2, 0) >> 'x'
        board.getFieldValue(2, 1) >> '_'
        board.getFieldValue(2, 2) >> 'x'
        when:
        new TicTacToe(null, state, board, playersPool).evaluateStateOfTheGame(new org.pepe.games.common.board.BoardGameInput(row, column, symbol))
        then:
        state.state == result

        where:
        hasNoSpace | symbol | row | column | result
        true       | 'x'    | 0   | 1      | GameState.State.DONE
        true       | 'x'    | 1   | 2      | GameState.State.DONE
        true       | 'x'    | 2   | 0      | GameState.State.DONE
        true       | 'x'    | 2   | 2      | GameState.State.DONE
        true       | '_'    | 2   | 1      | GameState.State.DONE
        false      | '_'    | 1   | 0      | GameState.State.ONGOING
        false      | '_'    | 2   | 1      | GameState.State.ONGOING
    }
}
