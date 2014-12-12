package com.musala.core

import com.musala.db.Article
import com.musala.db.Category
import com.musala.db.Site
import com.musala.repository.ArticleRepository
import com.musala.repository.SiteRepository
import com.musala.service.CategoryServiceImpl
import org.jsoup.nodes.Document
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import spock.lang.Ignore
import spock.lang.Specification

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

//@ContextConfiguration(classes = [GetTextFromPagesConfiguration.class, DatabaseTestConfiguration.class])
@EnableJpaRepositories(basePackages = "com.musala.repository")
@ComponentScan(basePackages = "com.musala.core")
class GetTextFromPagesTest extends Specification {

    GetTextFromPages getTextUnderTest
    SiteRepository siteRepository
    ArticleRepository articleRepository
    CategoryServiceImpl categoryServiceImpl
    Map<String, Set<String>> articlesCategories = [:]


    Site siteWithArticle
    Document document

    def setup() {
        getTextUnderTest = new GetTextFromPages()
        siteRepository = Mock(SiteRepository)
        articleRepository = Mock(ArticleRepository)
        categoryServiceImpl = Mock(CategoryServiceImpl)

        getTextUnderTest.siteRepository = siteRepository
        getTextUnderTest.articleRepository = articleRepository
        getTextUnderTest.categoryServiceImpl = categoryServiceImpl
        getTextUnderTest.articlesCategories = articlesCategories
    }

    def "add categories to article"() {
        given:
        String link = "http://www.technews.bg/article-76567.html"
        Category category = new Category();
        category.setCategoryName("category1")

        articlesCategories.put(link, ["category1"] as Set)
        Article article = new Article();

        when:
        categoryServiceImpl.findByCategoryName(_) >> category
        Article result = getTextUnderTest.addCategoriesToArticle(link, article)

        then:
        result != null
        article in category.getArticles() == true
        result.getCategories().size() == 1
        result.getCategories().each {
            it.getCategoryName() == "category1"
        }
    }

    def 'check if extractArticleText() method gets text right'() {
        given:
        String title = "Aliquam volutpat massa fermentum gravida auctor."
        def paragraphs = ["Vestibulum et odio ipsum. In vitae dignissim magna. Morbi mollis metus mauris, ut porttitor odio convallis eget. Donec diam tortor, maximus sed auctor suscipit.",
                          "Aliquam volutpat massa fermentum gravida auctor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.",
                          "Morbi in leo pharetra, luctus justo a, cursus nisl. Nulla facilisi. Nunc lacinia hendrerit ex, ac sagittis nisl bibendum id."]
        String htmlPageContent = """<!DOCTYPE html>
<html>
<body>
<h1>${title}</h1>
<div class="content">
<div id="attachment_35772" class="wp-caption alignright"><img class="size-full wp-image-35772" src="http://example.com/img/imaginary.jpg" width="250" height="201" />

<p><strong>${paragraphs[0]}</strong></p>

<p>${paragraphs[1]}</p>

<p>${paragraphs[2]}</p>

</body>
</html>
"""
        Site exampleSite = Mock(Site);
        exampleSite.getTextContentTag() >> "div.content"
        exampleSite.getTitleTag() >> "h1"

        when:
        siteRepository.findOne(_) >> exampleSite
        articleRepository.save(_) >> Mock(Article)

        def article = getTextUnderTest.extractArticleText(htmlPageContent)

        then:
        article.getTitle() == title
        article.getCategories().size() == 0
        article.getArticleText() == paragraphs.join(" ")
        0 * getTextUnderTest._
    }

    def "check if present in the DB categories are correct added to the Article entity"() {
        given:
        String linkOne = "www.example.com"
        def categories = ["category1", "category2", "3"] as Set
        getTextUnderTest.articlesCategories.put(linkOne, categories)
        Article article = new Article()

        categories.each { it ->
            def category = new Category()
            category.setCategoryName(it)
            categoryServiceImpl.findByCategoryName(_ as String) >> category
        }

        when:
        getTextUnderTest.addCategoriesToArticle(linkOne, article)

        then:
        article.getCategories().size() == 3
        categories.each {
            it in article.getCategories() == true
        }
        getTextUnderTest.articlesCategories.size() == 1
    }

    @Ignore
    def "check if NOT present in the DB, categories are correct added to the Article entity"() {
        given:
        String linkOne = "www.example.com"
        def presentCategories = ["category1"] as Set
        def nonPresentCategories = ["category2"] as Set
        getTextUnderTest.articlesCategories.put(linkOne, presentCategories + nonPresentCategories)
        Article article = new Article()

        def findCategoryClosure = { categoryName, isNull ->
            def category = new Category()
            category.setCategoryName(categoryName)
            categoryServiceImpl.findByCategoryName(_ as String) >> (isNull == null ? null : category)
        }

        presentCategories.each {
            findCategoryClosure(it, " ")
        }

        nonPresentCategories.each {
            findCategoryClosure(it, null)
        }

        when:
        getTextUnderTest.addCategoriesToArticle(linkOne, article)

        then:
        nonPresentCategories.each {
            categoryServiceImpl.findByCategoryName(it).getCategoryName() == null
        }
        presentCategories.each {
            categoryServiceImpl.findByCategoryName(it).getCategoryName() == it
        }
        article.getCategories().size() == 1
    }
}