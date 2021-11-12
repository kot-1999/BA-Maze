package game.server.controller;

import game.entity.CommentEntity;
import game.gameLogic.characters.Player;
import game.services.CommentService;
import game.services.PlayerService;
import game.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("maze/")
public class CommentsController {
    @Autowired
    private UserController userController;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping("comments")
    public String comments(Model model){
        model.addAttribute("comments",TemplateShaplones.getCommentsHtml(commentService,ratingService,playerService));
        return "comments";
    }

    @RequestMapping("comments/addComment")
    public String addComment(String comment,Model model){
        Player player= userController.getPlayer();
        if(player==null || player.getName().equals("Player"))
            return "redirect:/autorization";
        commentService.addComment(new CommentEntity(player.getName(),comment));

        return "redirect:/maze/comments";
    }
}
