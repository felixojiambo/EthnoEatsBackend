package com.ethnoeats.community;
import com.ethnoeats.community.ForumPost;
import com.ethnoeats.community.ForumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/forum-posts")
public class ForumPostController {

    @Autowired
    private ForumPostRepository forumPostRepository;

    @GetMapping
    public ResponseEntity<List<ForumPost>> getRecentPosts() {
        List<ForumPost> recentPosts = forumPostRepository.findTop10ByOrderByDateDesc();
        return ResponseEntity.ok(recentPosts);
    }
}
