package com.javarticle.spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by darrell-shofstall on 8/16/17.
 */
public class ImmutableArticleDTO {
    private static final long serialVersionUID = 1L;
    private final int articleId;
    @Size(min = 0, max = 100, message = "articles.error.title.size")
    private final String title;
    @NotNull(message = "articles.error.category.notnull")
    @Size(min = 1, max = 30, message = "articles.error.category.size")
    private final String category;


    private ImmutableArticleDTO(ArticleDTOBuilder builder) {
        this.articleId = builder.articleId;
        this.title = builder.title;
        this.category = builder.category;
    }

    public static class ArticleDTOBuilder {
        private final int articleId;
        private final String category;
        private String title;


        public ArticleDTOBuilder(int articleId, String category) {
            this.articleId = articleId;
            this.category = category;
        }

        public ArticleDTOBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ImmutableArticleDTO build() {
            return new ImmutableArticleDTO(this);
        }

    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ArticleDTOBuilder{" +
                "articleId=" + articleId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
