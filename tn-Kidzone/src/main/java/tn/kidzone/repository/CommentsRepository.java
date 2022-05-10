package tn.kidzone.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.kidzone.entity.Comments;
import tn.kidzone.entity.Post;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long>{
	
	@Query( "Select c from Comments c where c.post = ?1")
	List<Comments> afficherCommByidPost(Post P);
}
