package com.location.keyword.service;

import com.location.keyword.model.Keyword;
import com.location.keyword.repository.KeywordDao;
import com.location.keyword.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class KeywordService {
    @Autowired
    private KeywordDao keywordDao;

    @Transactional
    public void add(String keyword) {
        keywordDao.keywordIncreseCount(keyword);
    }

    public List<Keyword> topUsed() {
        return keywordDao.topUsedKeywords();
    }
}
