package com.musala.core

import com.musala.db.Site
import com.musala.repository.SiteRepository
import com.musala.testutils.DatabaseTestConfiguration
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.BeforeTransaction
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

@ComponentScan(basePackages = "com.musala.core")
class GetTextFromPagesTest extends Specification {

    @Rule
    TemporaryFolder tempFolder = new TemporaryFolder()

    GetTextFromPages getTextFromPages = new GetTextFromPages()

    SiteRepository siteRepository
    Site siteWithArticle
    Document document

    @BeforeTransaction
    def 'insert tuple in Site table'() {


    }

    def setup() {
        siteWithArticle = Stub(Site) {
            getTextContentTag() >> "div.content"
            getTitleTag() >> "h1"

        }

        siteRepository = Stub(SiteRepository) {
            findOne(_) >> siteWithArticle
        }

//        document = Stub(GetTextFromPages) {
//            getDocument(_ as String)>> Jsoup.parse(_ as String)
//        }

    }

    def 'test extractArticleText()'() {
        given:
        File createdFolder = tempFolder.newFolder("newfolder")
        File createdFile = tempFolder.newFile("newsText.html")
        createdFile.withWriter('UTF-8') { writer ->
            writer.write("""<!DOCTYPE html>
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
""")

            when:
            getTextFromPages.extractArticleText(createdFile.getText('UTF-8'))

        }

    }
}