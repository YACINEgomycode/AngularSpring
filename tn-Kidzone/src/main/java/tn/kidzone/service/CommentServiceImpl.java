package tn.kidzone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.kidzone.entity.Comments;
import tn.kidzone.entity.Post;
import tn.kidzone.repository.CommentsRepository;
import tn.kidzone.repository.PostRepository;

@Service
public class CommentServiceImpl implements ICommentsService{

	@Autowired
	CommentsRepository commentsRepository;
	@Autowired
	PostRepository postRepository;
	
	
	@Override
	public List<Comments> retrieveAllCommentsbyPost(Long idPost) {
		Post P = postRepository.findById(idPost).orElse(null);
		return commentsRepository.afficherCommByidPost(P);
	}

	@Override
	public Comments addComments(Comments c,Long idPost) {
		Post P = postRepository.findById(idPost).orElse(null);
		c.setPost(P);
		commentsRepository.save(c);
		return c;
	}

	 
	@Override
	public Comments updateComments(Comments c) {
		commentsRepository.save(c);
		return c;
	}

	

	@Override
	public void removeComment(Long id) {
		Comments C =commentsRepository.findById(id).orElse(null);
		commentsRepository.delete(C);
	}

	@Override
	public List<Comments> retrieveAllComments() {
		return commentsRepository.findAll();
	}
	

}
