package com.location.keyword.repository;

import com.location.keyword.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = "2000")
    })
    Optional<Keyword> findById(String s);

    List<Keyword> findTop10ByOrderByCountDesc();
}
