package com.javarticle.spring.common;

import com.javarticle.spring.service.ArticleService;
import com.javarticle.spring.service.IArticleService;
import navis.injection.BuildFunction;
import navis.injection.InstanceFactory;

/**
 * Created by darrell-shofstall on 8/16/17.
 */
public class CommonDependencies {

    public static final InstanceFactory<ArticleService> articleService =
            new InstanceFactory<>(BuildFunction.singletonBuilder(ArticleService.class));
}
