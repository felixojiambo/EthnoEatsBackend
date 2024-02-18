package com.ethnoeats.community;



import com.ethnoeats.community.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {
    List<ForumPost> findTop10ByOrderByDateDesc();
}
