package com.gitittech.paygo.settings.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitittech.ambagapequery.predicates.PredicateBuilder;
import com.gitittech.paygo.entities.JpaSetting;
import com.querydsl.core.types.dsl.Expressions;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface ISettingsReadRepository extends Repository<JpaSetting, Long> , QuerydslPredicateExecutor<JpaSetting>{

    String PATH_META_DATA = "jpaSetting";    
    
    default Page<JpaSetting> getSettings(Boolean isSearch, String filter,
             Integer page, Integer size,
             Sort.Direction direction, String... properties) throws JsonProcessingException {
        var predicate = isSearch
                ? new PredicateBuilder<>(PATH_META_DATA, JpaSetting.class)
                        .buildFromJson(filter) : Expressions.TRUE.isTrue();
        return findAll(predicate, PageRequest.of(page, size, direction, properties));
    }
    
    default Optional<JpaSetting>  findByKey(String key){        
        var exp = new PredicateBuilder(PATH_META_DATA, JpaSetting.class)
                .with("name", ":", key)
                .buildAndCombination();
        return findOne(exp);
    
    }
}
