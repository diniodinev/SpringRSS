package com.musala.repository;

import com.musala.db.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    public Article findByLink(String link);

    void delete(Article article);

    //@Query("select u from User u where u.firstname like %?1")
    @Query("SELECT a FROM Article a WHERE a.title LIKE %?1%")
    List<Article> findByKeyWord(String keyWord);

}
