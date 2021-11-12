package game.server.controller;

import game.entity.CommentEntity;
import game.entity.PlayerEntity;
import game.entity.RaitingEntity;
import game.gameLogic.Maze;
import game.gameLogic.cells.Cell;
import game.gameLogic.cells.CellSide;
import game.gameLogic.cells.CellWithCoin;
import game.gameLogic.cells.EndCell;
import game.gameLogic.characters.Player;
import game.gameLogic.levelBuilder.LevelBuilder;
import game.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

public class TemplateShaplones {

    public static String getMenu(){
        StringBuilder stringBuilder=new StringBuilder();
        String[] str= {"restart", "Restart","topPlayers", "Top Players","comments", "Comments","autorization","Log in","logout","Log out"};
        for (int i=0;i<str.length;i+=2) {
            stringBuilder.append("<a href=\"/maze/"+str[i]+"\" class=\"nav-link\">\n" +
                    "                <div class=\"nav-btn\">\n" +
                    "                     "+str[i+1]+"\n" +
                    "                </div>\n" +
                    "            </a>");
        }


        return stringBuilder.toString();
    }

    public static String getFieldHtml(Maze maze, Player player){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<table>\n");
//        stringBuilder.append("<p id=\"player\"></p>");
        for(int y = 0; y< maze.getHight(); y++) {
            stringBuilder.append("<tr>\n");
            for (int x = 0; x < maze.getWidth(); x++) {
                Cell c=maze.getCell(x,y);
                stringBuilder.append("<td class=\"grid "+getSides(c)+"\">\n");
                if(x== player.getXPosition() && y== player.getYPosition())
                    stringBuilder.append("<p id=\"player\"></p>");
                if(c.hasItem())
                    stringBuilder.append("<div class=\""+getItems(maze.getCell(x,y))+"\"></div>\n");
                stringBuilder.append("</td>\n");
            }
            stringBuilder.append("</tr>\n");
        }
        stringBuilder.append("</table>\n");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String getSides(Cell c){
        String s="";
        if(c.hasWall(CellSide.LEFT)==false)
            s+="left ";
        if(c.hasWall(CellSide.RIGHT)==false)
            s+="right ";
        if(c.hasWall(CellSide.UP)==false)
            s+="up ";
        if(c.hasWall(CellSide.BOTTOM)==false)
            s+="bottom ";
        return s;
    }

    public static String getItems(Cell c){
        String s="";
        if(c instanceof CellWithCoin && c.hasItem())
            s+="coin ";
        if(c instanceof EndCell)
            s+="end ";
        return s;
    }


    public static String getCommentsHtml(CommentService commentService, RatingService ratingService, PlayerService playerService){
        StringBuilder stringBuilder=new StringBuilder();
        PlayerEntity playerEntity=null;
        List<CommentEntity> list=commentService.getComments("Maze");
        for (int i=list.size()-1;i>=0;i--) {
            CommentEntity entity=list.get(i);
            String rating,img="https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg";
            try{
                rating=String.valueOf( ratingService.getRating("Maze",entity.getPlayer()))+"/10";
            }catch (RatingException e){
                rating=e.getMessage();
            }
            try{
                playerEntity=playerService.getPlayer(entity.getPlayer());
            } catch(PlayerExeption playerExeption) {
                continue;
            }
            System.out.println(playerEntity.getAvatarUrl()+"--------------------------");
            if(playerEntity!=null && playerEntity.getAvatarUrl()!=null && !playerEntity.getAvatarUrl().equals("") && !playerEntity.getAvatarUrl().equals(" "))
                img=playerEntity.getAvatarUrl();
            stringBuilder.append("<div class=\"comment\">\n" +
                    "               <img class=\"comment-image\" src=\""+img+"\"/>\n" +
                    "               <h3 class=\"comment-text\">"+entity.getPlayer()+"</h3>\n" +
                    "               <p class=\"comment-text intro\">"+entity.getComment()+"</p>\n" +
                    "               <p class=\"comment-text comment-date\">"+rating+"</p>\n" +
                    "           </div>");
        }

        return stringBuilder.toString();
    }


    public static String rating( RatingService ratingService,Player player){
        StringBuilder stringBuilder=new StringBuilder();
        int average=ratingService.getAverageRating("Maze");
        for(int i=1;i<=10;i++){
            if(i<=average)
                stringBuilder.append("<a class=\"rating checked\" href=\"maze/rate?rating="+i+"\">X</a>");
            else
                stringBuilder.append("<a class=\"rating\" href=\"maze/rate?rating="+i+"\">X</a>");
        }
        if(player!=null && player.getName().equals("Player")==false)
        stringBuilder.append("<br>Your rate: "+ratingService.getRating("Maze",player.getName()));

        return stringBuilder.toString();
    }



}
