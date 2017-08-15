package com.javarticle.spring.controller;

/**
 * Created by darrell-shofstall on 8/9/17.
 */

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import com.javarticle.spring.dto.ArticleDTO;
import com.javarticle.spring.dto.ArticlesDTO;
import com.javarticle.spring.dto.PageableDTO;
import com.javarticle.spring.entity.Article;
import com.javarticle.spring.service.IArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("user")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "article/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable("id") Integer id) {
        Article article = articleService.getArticleById(id);
        ArticleDTO articleDto = convertToDto(article);
        return new ResponseEntity<ArticleDTO>(articleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<Article> list = articleService.getAllArticles();
        List<ArticleDTO> listDto = list.stream().map(item -> convertToDto(item)).collect(Collectors.toList());
        return new ResponseEntity<List<ArticleDTO>>(listDto, HttpStatus.OK);
    }

    @RequestMapping(value = "articlesByPage", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleDTO>> getPage(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {

        Page<Article> articles = articleService.getPage(pageNumber);
        List<ArticleDTO> listDto = articles.map(article -> convertToDto(article)).getContent();

        return new ResponseEntity(buildResponseDto(articles, listDto), HttpStatus.OK);
    }

    @RequestMapping(value = "article", method = RequestMethod.POST)
    public ResponseEntity<Void> addArticle(@Validated @RequestBody ArticleDTO articleDto, UriComponentsBuilder builder) throws ParseException {
        Article  article = convertToEntity(articleDto);
        boolean flag = articleService.addArticle(article);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "article", method = RequestMethod.PUT)
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDto) throws ParseException {
        Article article = convertToEntity(articleDto);
        articleService.updateArticle(article);
        return new ResponseEntity<ArticleDTO>(articleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "article/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    private ArticleDTO convertToDto(Article article) {
        ArticleDTO articleDto = modelMapper.map(article, ArticleDTO.class);
        return articleDto;
    }

    private Article convertToEntity(ArticleDTO articleDto) throws ParseException {
        Article article = modelMapper.map(articleDto, Article.class);

        return article;

    }

    private ArticlesDTO buildResponseDto(Page<Article> articles, List<ArticleDTO> listDto ) {

        ArticlesDTO responseDto = new ArticlesDTO();
        PageableDTO pageableDTO = new PageableDTO(articles);
        responseDto.setArticles(listDto);
        responseDto.setPageableDTO(pageableDTO);

        return responseDto;
    }
}
