package com.musala.core; 
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


import java.util.ArrayList;
import java.util.List;

public class RssExtractorSubject {

    private List<SaxObserver> observers = new ArrayList<SaxObserver>();


    /**
     * Attach oserver to this subject
     *
     * @param observer
     */
    public void attach(SaxObserver observer) {
        observers.add(observer);
    }

//    public void notifyAllObservers(String element) {
//        for (SaxObserver observer : observers) {
//            observer.update("My element:" + element);
//        }
//    }

    public void notifyCategoryObserver(String element){
        for(SaxObserver observer: observers) {
            if(observer instanceof CategoryObserver){
                observer.update(element);
            }
        }
    }

    public void notifyRssUrlsObserver(String element){
        for(SaxObserver observer: observers) {
            if(observer instanceof RssUrlsObserver){
                observer.update(element);
            }
        }

    }

    public void addNewElement(String element) {
        System.out.println("Call category observer");
        notifyCategoryObserver("category News "+ element);

        System.out.println("Call rssUrls observer");
        notifyCategoryObserver("som url"+ element);

    }


}
