package com.javarticle.spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by darrell-shofstall on 8/11/17.
 */
public class ArticleDTO {

    private static final long serialVersionUID = 1L;

    private int articleId;
    @Size(min = 0, max = 100, message = "articles.error.title.size")
    private String title;
    @NotNull(message = "articles.error.category.notnull")
    @Size(min = 1, max = 30, message = "articles.error.category.size")
    private String category;

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
