package tn.kidzone.service;

import java.util.List;

import tn.kidzone.entity.Comments;
import tn.kidzone.entity.Post;




public interface ICommentsService {
	
	List<Comments> retrieveAllCommentsbyPost(Long idPost);
	
	List<Comments> retrieveAllComments();

	Comments addComments(Comments c,Long idPost);

	Comments updateComments(Comments c);

	void removeComment(Long id);
}
