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

//Subject in Observer Pattern
public interface RssProcessor {
    /**
     * Register given observer to the RssProcessor
     *
     * @param observer
     */
    public void register(ArticleObserver observer);

    /**
     * Unregister given observer from the RessProcessor
     *
     * @param observer
     */
    public void unRegister(ArticleObserver observer);

    /**
     * Notify all registered observers
     */
    public void notifyAllObservers();

    /**
     * Get update from given <br>observer<br/>
     *
     * @param observer
     * @return
     */
    public ArticleInfo getUpdate();
}
