package com.musala.service;


import com.musala.db.Site;
import com.musala.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public Iterable<Site> findAll() {
        return siteRepository.findAll();
    }

    public Site findOne(String id) {
        return siteRepository.findOne(id);
    }
}
