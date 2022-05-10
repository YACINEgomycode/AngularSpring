package tn.kidzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.kidzone.entity.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
