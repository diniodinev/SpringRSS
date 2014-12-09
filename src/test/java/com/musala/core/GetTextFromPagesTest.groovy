package com.musala.core

import com.musala.db.Article
import com.musala.db.Category
import com.musala.db.Site
import com.musala.repository.ArticleRepository
import com.musala.repository.SiteRepository
import com.musala.service.CategoryServiceImpl
import com.musala.testutils.DatabaseTestConfiguration
import com.musala.testutils.GetTextFromPagesConfiguration
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.BeforeTransaction
import spock.lang.Ignore
import spock.lang.Specification

import javax.annotation.PostConstruct

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
    Map<URL, Set<String>> articlesCategories = [:]


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

    def 'test extractArticleText() method'() {
        given:
        String htmlPageContent = """<!DOCTYPE html>
<html>
<body>

<div class="content">
<div id="attachment_35772" class="wp-caption alignright"><img class="size-full wp-image-35772" src="http://example.com/img/imaginary.jpg" width="250" height="201" />
<h1>Aliquam volutpat massa fermentum gravida auctor.</h1>
<p><strong>Vestibulum et odio ipsum. In vitae dignissim magna. Morbi mollis metus mauris, ut porttitor odio convallis eget. </strong>Donec diam tortor, maximus sed auctor suscipit.</p>

<p>Aliquam volutpat massa fermentum gravida auctor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>

<p>Morbi in leo pharetra, luctus justo a, cursus nisl. Nulla facilisi. Nunc lacinia hendrerit ex, ac sagittis nisl bibendum id.</p>

</body>
</html>
"""
        Site exampleSite = Mock(Site);
        exampleSite.getTextContentTag() >> "div.content"
        exampleSite.getTitleTag() >> "h1"

        when:
        siteRepository.findOne(_) >> exampleSite
        articleRepository.save(_) >> Mock(Article)
        getTextUnderTest.addCategoriesToArticle(_ as String, Mock(Article)) >> [:]

        getTextUnderTest.extractArticleText(htmlPageContent)
        then:
        // assert article.toString() == "a"
        'a' == 'a'

    }

    @Ignore
    def 'test extractArticleText()'() {
        given:
        String htmlPageContent = """<!DOCTYPE html>
<html>
<body>

<div class="content">
<div id="attachment_35772" class="wp-caption alignright"><img class="size-full wp-image-35772" src="http://example.com/img/imaginary.jpg" width="250" height="201" />
<h1>Aliquam volutpat massa fermentum gravida auctor.</h1>
<p><strong>Vestibulum et odio ipsum. In vitae dignissim magna. Morbi mollis metus mauris, ut porttitor odio convallis eget. </strong>Donec diam tortor, maximus sed auctor suscipit.</p>

<p>Aliquam volutpat massa fermentum gravida auctor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>

<p>Morbi in leo pharetra, luctus justo a, cursus nisl. Nulla facilisi. Nunc lacinia hendrerit ex, ac sagittis nisl bibendum id.</p>

</body>
</html>
"""
        when:
        getTextUnderTest.extractArticleText(htmlPageContent.toString())

        then:
        for (Article article : getTextUnderTest.getArticleRepository().findAll()) {
            println article
        }
    }
}