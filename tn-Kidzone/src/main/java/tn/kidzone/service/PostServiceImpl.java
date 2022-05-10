package tn.kidzone.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.kidzone.entity.Comments;
import tn.kidzone.entity.Post;
import tn.kidzone.repository.PostRepository;

@Service
public class PostServiceImpl implements IPostService{

	@Autowired
	PostRepository postRepository;
	
	@Override
	public Post addPost(Post Im,MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			Im.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return postRepository.save(Im);
		
	}

	@Override
	public void deletePost(Long postid) {
		Post P = postRepository.findById(postid).orElse(null);
		postRepository.delete(P);
	}	
	@Override
	public Post updatePost(Post P) {
		return postRepository.save(P);
	}

	@Override
	public List<Post> retrieveAllPosts() {
		return (List<Post>)postRepository.findAll();
	}

	@Override
	public Post retrievePostById(Long idPost) {
		return postRepository.findById(idPost).orElse(null);
	}

	@Override
	public List<Post> retrieveAllPosts(int pageNo, int pageSize) {
		PageRequest paging = PageRequest.of(pageNo, pageSize);
		Page<Post> pageResult = postRepository.findAll(paging);
		return pageResult.toList();
	}
	
	}



