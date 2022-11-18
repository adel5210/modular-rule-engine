package com.adel.modularruleengine.repository;

import com.adel.modularruleengine.model.RulesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RuleRepository extends JpaRepository<RulesGroup, Long>, JpaSpecificationExecutor<RulesGroup> {

    @Query("select r from RulesGroup r where r.groupName = ?1")
    RulesGroup findByName(String groupName);

}