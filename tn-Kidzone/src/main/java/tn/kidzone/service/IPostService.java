package tn.kidzone.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.kidzone.entity.Comments;
import tn.kidzone.entity.Post;

public interface IPostService {

	Post addPost(Post Im, MultipartFile file);
	void deletePost(Long postid);
	Post updatePost(Post P);
	List<Post> retrieveAllPosts();
	List<Post> retrieveAllPosts(int pageNo,int pageSize);

	Post retrievePostById(Long idPost);

}
