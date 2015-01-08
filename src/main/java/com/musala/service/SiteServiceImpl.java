package com.musala.service;


import com.musala.db.Site;
import com.musala.repository.ArticleRepository;
import com.musala.repository.SiteRepository;
import com.musala.view.SiteView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SiteServiceImpl implements SiteService {
    private static final Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);

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
        return siteRepository.save(new Site(siteView.getSiteName(), siteView.getRssLink(), siteView.getRssTag(), siteView.getTitleTag(), siteView.getTextContentTag(), siteView.getCategoryTag(), siteView.getLastVisitDateTag(), siteView.getLastVisitDate()));
    }

    @Override
    public void delete(String deleteName) {
        siteRepository.delete(siteRepository.findOne(deleteName));
    }

    @Override
    @Transactional
    public void update(SiteView newSiteView) {
        if (newSiteView.getSiteName() == null) {
            logger.info("Trying to update Site with null SiteView :" + newSiteView);
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

    @Override
    @Transactional
    public void updateLastVisitDate(Site site, String lastVisit) {
        if (lastVisit == null && lastVisit.isEmpty()) {
            logger.info("Trying to update lastVisitDate with not meaningful String :" + lastVisit);
            return;
        }
        if (!siteRepository.exists(site.getSiteName())) {
            logger.info("Create new site object in the DB instead of only update lastVisitDate");
            site.setLastVisitDate(lastVisit);
            save(site);
        } else {
            Site oldEntity = siteRepository.findOne(site.getSiteName());
            oldEntity.setLastVisitDate(lastVisit);
        }

    }

    public SiteRepository getSiteRepository() {
        return siteRepository;
    }

    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
