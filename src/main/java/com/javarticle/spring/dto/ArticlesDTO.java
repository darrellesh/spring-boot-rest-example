package com.javarticle.spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by darrell-shofstall on 8/11/17.
 */
public class ArticlesDTO {

    private static final long serialVersionUID = 1L;

    private List<ArticleDTO> articles;
    private PageableDTO pageableDTO;

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public PageableDTO getPageableDTO() {
        return pageableDTO;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    public void setPageableDTO(PageableDTO pageableDTO) {
        this.pageableDTO = pageableDTO;
    }
}
