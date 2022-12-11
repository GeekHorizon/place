package com.location.keyword.repository;

import com.location.keyword.model.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KeywordDao {
    @Autowired
    private KeywordRepository keywordRepository;

    public void keywordIncreseCount(String name) {
        Keyword keyword = keywordRepository.findById(name).orElseGet(() -> new Keyword(name, 0));
        keyword.setCount(keyword.getCount() + 1);
        keywordRepository.save(keyword);
    }

    public List<Keyword> topUsedKeywords() {
        return keywordRepository.findTop10ByOrderByCountDesc();
    }
}
