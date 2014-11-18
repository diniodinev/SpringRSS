package com.dinio.core;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssGetLinks extends DefaultHandler {
    private List<URL> links = new ArrayList<URL>();
    private StringBuilder text;

    void readData(URL sourceUrl) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new InputSource(sourceUrl.openStream()), this);
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("feedburner:origLink")) {
            text = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        text = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (text != null) {
            text.append(ch, start, length);
            try {
                links.add(new URL(text.toString()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<URL> getLinks() {
        return links;
    }

}
