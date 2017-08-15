package com.javarticle.spring.dto;

import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by darrell-shofstall on 8/11/17.
 */
public class ArticlesDTO  extends PageableDTO {

    private static final long serialVersionUID = 1L;

    private List<ArticleDTO> articles;

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    public void setPageableDTO(Page page) {
        this.setCurrentPageNumber(page.getNumber());
        this.setTotalElements(page.getTotalElements());
        this.setTotalPages(page.getTotalPages());
        this.setHasNextPage(page.hasNext());
        this.setHasPreviousPage(page.hasPrevious());
        this.setFirstPage(page.isFirst());
        this.setLastPage(page.isLast());
        this.setNumberOfElements(page.getNumberOfElements());
        this.setPageSize(page.getSize());
    }


    public ArticlesDTO() {
    }

}
