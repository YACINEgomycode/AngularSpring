package tn.kidzone.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import tn.kidzone.entity.Comments;
import tn.kidzone.entity.Post;
import tn.kidzone.service.IPostService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class PostController {

	@Autowired
	IPostService iPostService;

//	@PostMapping("/addP")
	@RequestMapping(value = "/addP", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseBody
	public Post savePost(Post p, @RequestParam("file") MultipartFile file)

	{

		return iPostService.addPost(p, file);
	}

	@DeleteMapping("/remove-Post/{post-id}")
	@ResponseBody
	public void removeOperateur(@PathVariable("post-id") Long postId) {
		iPostService.deletePost(postId);
	}

	@PutMapping("/modifierPost")
	@ResponseBody
	Post ModifierOperateur(@RequestBody Post P) {
		return iPostService.updatePost(P);
	}

	@GetMapping("/retrieve-all-Posts")
	@ResponseBody
	public List<Post> getPosts() {
		List<Post> listPosts = iPostService.retrieveAllPosts();
		return listPosts;
	}
	@GetMapping("/get-Post/{post-id}")
	@ResponseBody
	public Post getPostById(@PathVariable("post-id") Long postId) {
		return iPostService.retrievePostById(postId);
	}
	
	@GetMapping("/retrieve-AllPosts/{pageNo}/{pageSize}")
	 @ResponseBody
	 public List<Post> getAllPosts(@PathVariable int pageNo, @PathVariable int pageSize) {
		
	 return  iPostService.retrieveAllPosts(pageNo, pageSize);
	 
	 }
	
}
