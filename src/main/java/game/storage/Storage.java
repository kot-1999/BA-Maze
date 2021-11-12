package game.storage;

import game.services.PlayerExeption;

import java.util.List;

public interface Storage {
    InfoForm autorizePlayer(InfoForm infoForm) throws PlayerExeption;

    void updateScore(InfoForm infoForm);
    void insertComment(CommentForm commentForm);

    List<InfoForm> getRaiting(int numberOfBestPlayers);
    List<CommentForm> getComments();

    void refresh();
}
