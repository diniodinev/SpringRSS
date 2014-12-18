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
import com.musala.db.Site;
import com.musala.view.SiteView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class SiteViewConverter implements Converter<Site, SiteView> {

    @Override
    public SiteView convert(Site site) {
        return new SiteView(site.getSiteName(), site.getRssLink(), site.getRssTag(), site.getTitleTag(), site.getTextContentTag(), site.getCategoryTag());
    }
}
