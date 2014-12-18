package com.musala.converter; 
 /*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
  * Created by dinyo.dinev on 2014.
 */


import com.musala.db.Article;
import com.musala.db.Category;
import com.musala.view.ArticleView;
import com.musala.view.SiteView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleViewConverter implements Converter<Article, ArticleView> {

    @Override
    public ArticleView convert(Article sourceArticle) {
        return new ArticleView(sourceArticle.getId(), sourceArticle.getLink(), sourceArticle.getArticleText(), sourceArticle.getTitle(), sourceArticle.getPublicationDate(), getCategories(sourceArticle.getCategories()), sourceArticle.getSite().getSiteName());
    }

    /**
     * Convert given List<Category> to List<String> using names of the Category saved in
     * its categoryName parameter
     * @param categoryEntities
     * @return
     */
    private List<String> getCategories(List<Category> categoryEntities) {
        List<String> categoryNames = new ArrayList<String>();
        for (Category category : categoryEntities) {
            categoryNames.add(category.getCategoryName());
        }
        return categoryNames;
    }
}