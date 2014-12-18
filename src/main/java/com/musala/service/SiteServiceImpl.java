package com.musala.service;


import com.musala.db.Article;
import com.musala.db.Site;
import com.musala.repository.ArticleRepository;
import com.musala.repository.SiteRepository;
import com.musala.view.SiteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Iterable<Site> findAll() {
        return siteRepository.findAll();
    }

    @Override
    public Site findOne(String id) {
        return siteRepository.findOne(id);
    }

    @Override
    public Site save(Site site) {
        return siteRepository.save(site);
    }

    @Override
    public Site save(SiteView siteView) {
        return siteRepository.save(new Site(siteView.getSiteName(), siteView.getRssLink(), siteView.getRssTag(), siteView.getTitleTag(), siteView.getTextContentTag(), siteView.getCategoryTag()));
    }

    @Override
    public void delete(String deleteName) {
        siteRepository.delete(siteRepository.findOne(deleteName));
    }

    @Override
    @Transactional
    public void update(SiteView newSiteView) {
        System.out.println("In update Site.");
        if (newSiteView.getSiteName() == null) {
            return;
        }
        if (!siteRepository.exists(newSiteView.getSiteName())) {
            save(newSiteView);
        } else {
            Site oldEntity = siteRepository.findOne(newSiteView.getSiteName());
            oldEntity.setCategoryTag(newSiteView.getCategoryTag());
            oldEntity.setTextContentTag(newSiteView.getTextContentTag());
            oldEntity.setRssLink(newSiteView.getRssLink());
            oldEntity.setRssTag(newSiteView.getRssTag());
            oldEntity.setTitleTag(newSiteView.getTitleTag());
        }
    }

    public SiteRepository getSiteRepository() {
        return siteRepository;
    }

    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }
}
