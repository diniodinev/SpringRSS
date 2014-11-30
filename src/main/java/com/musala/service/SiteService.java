package com.musala.service;


import com.musala.db.SiteEntity;
import com.musala.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public Iterable<SiteEntity> findAll() {
        return siteRepository.findAll();
    }
}
