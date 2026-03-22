package org.springsecurity.recipeblogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springsecurity.recipeblogapplication.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
