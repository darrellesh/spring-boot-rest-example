package com.javarticle.spring.service;

import com.javarticle.spring.Repository.IArticleRepository;
import com.javarticle.spring.dao.IArticleDAO;
import com.javarticle.spring.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by darrell-shofstall on 8/9/17.
 */

@Service
public class ArticleService implements IArticleService {

    private final static int PAGESIZE = 3;

    @Autowired
    private IArticleDAO articleDAO;

    @Autowired
    private IArticleRepository articleRepository;

    @Override
    public Article getArticleById(int articleId) {
        Article obj = articleDAO.getArticleById(articleId);
        return obj;
    }
    @Override
    public List<Article> getAllArticles(){
        return articleDAO.getAllArticles();
    }
    @Override
    public synchronized boolean addArticle(Article article){
        if (articleDAO.articleExists(article.getTitle(), article.getCategory())) {
            return false;
        } else {
            articleDAO.addArticle(article);
            return true;
        }
    }
    @Override
    public void updateArticle(Article article) {
        articleDAO.updateArticle(article);
    }
    @Override
    public void deleteArticle(int articleId) {
        articleDAO.deleteArticle(articleId);
    }

    @Override
    public List<Article> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "articleId");

        return articleRepository.findAll(request).getContent();
    }
}
