package com.musala.service;


import com.musala.db.Site;
import com.musala.repository.SiteRepository;
import com.musala.view.SiteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

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

    public SiteRepository getSiteRepository() {
        return siteRepository;
    }

    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }
}
