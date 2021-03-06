package com.musala.service;
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


import com.musala.db.Site;
import com.musala.view.SiteView;

public interface SiteService {

    public Iterable<Site> findAll();

    public Site findOne(String id);

    public Site save(Site category);

    public Site save(SiteView category);

    public void delete(String deleteName);

    public void update(SiteView newSiteView);

    public  void updateLastVisitDate(Site site, String lastVisit);

}
