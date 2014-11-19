package com.dinio.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import static com.dinio.core.XML_RSS_URLS.COMPUTER_WORLD_RSS_LINK;

public class TestRss   {
    public static URL URL_LINK;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("RSSBean.xml");
        RssLinks links = context.getBean("rssLinksBean", RssLinks.class);
        GetTestFromPages textFromPagesHandler = context.getBean("getTestFromPages", GetTestFromPages.class);

        links.readData(COMPUTER_WORLD_RSS_LINK);
        List<IArticle> articles = textFromPagesHandler.readData(links.getLinks());
        for(IArticle article:articles){
            System.out.println(article);
        }
    }
}
