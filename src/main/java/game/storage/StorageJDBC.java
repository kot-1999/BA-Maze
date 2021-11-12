package game.storage;

import game.services.PlayerExeption;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StorageJDBC implements Storage {
    public static final String DBS_URL = "jdbc:postgresql://localhost:5432/gamejava";
    public static final String DBS_USER = "postgres";
    public static final String DBS_PASSWORD = "postgres";
    public static final String CREATE_PLAYERS = "CREATE TABLE IF NOT EXISTS players (name text PRIMARY KEY, email text, score integer);";
    public static final String CREATE_COMMENTS = "CREATE TABLE IF NOT EXISTS comments (player text REFERENCES players(name),coment text NOT NULL);";
    public static final String REFRESH = "DELETE from comments; DELETE from players;";
    public static final String INSERT_PLAYER = "INSERT INTO players(name,email,score) VALUES (?,?,?);";
    public static final String INSERT_COMMENT = "INSERT INTO comments(player, coment) VALUES (?,?);";
    public static final String UPDATE_SCORE = "UPDATE players SET score=?  WHERE name=?;";
    public static final String SELECT_SCORE = "SELECT name,email,score FROM players score WHERE name=?;";
    public static final String SELECT_LIMITED_SCORE = "SELECT name,email,score FROM players  ORDER BY    score DESC LIMIT 10;";
    public static final String SELECT_COMMENT = "SELECT player, coment FROM comments;";



    private static Connection connection;
    private static Statement playerStatement,commentsStatement;



    public static StorageJDBC connect(){
        StorageJDBC result=new StorageJDBC();

        try {
            connection = DriverManager.getConnection(DBS_URL, DBS_USER, DBS_PASSWORD);
            playerStatement =connection.createStatement();
            commentsStatement=connection.createStatement();

            commentsStatement.executeUpdate(CREATE_COMMENTS);
            playerStatement.executeUpdate(CREATE_PLAYERS);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }




    @Override
    public InfoForm autorizePlayer(InfoForm infoForm) throws PlayerExeption {
        try {
            PreparedStatement ps =connection.prepareStatement(INSERT_PLAYER);
            ps.setString(1,infoForm.getName());
            ps.setString(2,infoForm.getEmail());
            ps.setInt(3,infoForm.getScore());
            ps.execute();
        }catch (SQLException error){
            try{

                PreparedStatement pss =connection.prepareStatement(SELECT_SCORE);
                pss.setString(1,infoForm.getName());
                ResultSet resultSet= pss.executeQuery();

                resultSet.next();
                if(infoForm.getName().equals(resultSet.getString(1))==false ||
                        infoForm.getEmail().equals(resultSet.getString(2))==false)
                    throw new PlayerExeption();

                infoForm.setScore(resultSet.getInt(3));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return infoForm;
    }

    @Override
    public void updateScore(InfoForm infoForm) {
        try{

            PreparedStatement ps =connection.prepareStatement(SELECT_SCORE);

            ps.setString(1,infoForm.getName());
            ResultSet resultSet= ps.executeQuery();

            resultSet.next();
            int score=resultSet.getInt(3);

            if(infoForm.getScore()> score) {
                ps = connection.prepareStatement(UPDATE_SCORE);
                ps.setInt(1, infoForm.getScore());
                ps.setString(2, infoForm.getName());
                ps.execute();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<InfoForm> getRaiting(int numberOfBestPlayers) {
        List<InfoForm> list =new ArrayList<>();
        try {
            ResultSet resultSet= playerStatement.executeQuery(SELECT_LIMITED_SCORE);

            for(int i=0;i<numberOfBestPlayers && resultSet.next();i++){
                list.add(new InfoForm(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void insertComment(CommentForm commentForm) {
        try{

            PreparedStatement ps =connection.prepareStatement(INSERT_COMMENT);

            ps.setString(1,commentForm.getPlayer());
            ps.setString(2,commentForm.getComment());
            ps.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    private int currComment=0;
    private ResultSet resultSet=null;
    @Override
    public List<CommentForm> getComments() {
        List<CommentForm> list =new ArrayList<>();
        try {
            resultSet= commentsStatement.executeQuery(SELECT_COMMENT);
            while( resultSet.next())
                list.add(new CommentForm(resultSet.getString(1),resultSet.getString(2)));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void refresh() {
        try {
            playerStatement.executeUpdate(REFRESH);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
