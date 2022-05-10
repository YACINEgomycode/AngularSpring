package tn.kidzone.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.kidzone.entity.Comments;
import tn.kidzone.entity.Post;
import tn.kidzone.repository.PostRepository;
import tn.kidzone.service.ICommentsService;
import tn.kidzone.service.IPostService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class CommentsController {
	
	@Autowired
	ICommentsService iCommentsService;
	
	
	@GetMapping("/retrieve-Comms-by-Post/{post-id}")
	 @ResponseBody
	 public List<Comments> getCommsByPosts(@PathVariable("post-id") Long postId) {
	 return  iCommentsService.retrieveAllCommentsbyPost(postId);
	 }
	
	@GetMapping("/retrieve-AllComments")
	 @ResponseBody
	 public List<Comments> getAllComms() {
		
	 return  iCommentsService.retrieveAllComments();
	 
	 }
	
	 @PostMapping("/add-Comment/{post-id}")
	 @ResponseBody
	 public Comments addComment(@RequestBody Comments C,@PathVariable("post-id") Long postId)
	 {
	 return iCommentsService.addComments(C, postId);
	 }
	 
	 @PutMapping("/AlterComment")
	 @ResponseBody
	 Comments ModifierComment(@RequestBody Comments C)
		{
			return iCommentsService.updateComments(C);
		}

	 @DeleteMapping("/remove-Comment/{comment-id}")
	 @ResponseBody
	 public void removeOperateur(@PathVariable("comment-id") Long commentId) {
	 iCommentsService.removeComment(commentId);
	 }
}
