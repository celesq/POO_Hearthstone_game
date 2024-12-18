package org.poo.gameClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.poo.fileio.GameInput;
import org.poo.fileio.Input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayGame {
    private StartGame startGame;
    private final Input inputData;
    private final String filePath2;
    private int gameNumber = 0;
    private int playerOneWins = 0;
    private int playerTwoWins = 0;

    public PlayGame(final Input inputData, final String filePath2) {
        this.inputData = inputData;
        this.filePath2 = filePath2;
    }


    /**
     *
     * @return player one wins
     */
    public int getPlayerOneWins() {
        return playerOneWins;
    }

    /**
     * @param playerOneWins setter
     */
    public void setPlayerOneWins(final int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    /**
     * @return player two wins
     */
    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    /**
     *
     * @param playerTwoWins setter
     */
    public void setPlayerTwoWins(final int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }


    /**
     *
     * @throws IOException function for playing the game
     */
    public void play() throws IOException {
        ArrayList<GameInput> gameInput = inputData.getGames();
        startGame = new StartGame(inputData);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Output output = new Output(startGame);
        for (GameInput game : gameInput) {
            gameNumber++;
            startGame.setGame(inputData, game, output);
            output.iterateActions(game.getActions(), gameNumber, this);
            objectWriter.writeValue(new File(filePath2), output.getOutput());
        }
    }
}
