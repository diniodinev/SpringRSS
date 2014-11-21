package com.dinio.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TestRss   {
    public static URL URL_LINK;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("RSSBean.xml");
        ExtractUrlsFromRssXml links = context.getBean("extractUrlsFromRssXmlBean", ExtractUrlsFromRssXml.class);
        GetTestFromPages textFromPagesHandler = context.getBean("getTextFromPagesBean", GetTestFromPages.class);
        //links.readData();
        List<IArticle> articles = textFromPagesHandler.readData(links.getLinks());
        for(IArticle article:articles){
            System.out.println(article);
        }
    }
}
