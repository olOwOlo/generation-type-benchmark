package com.olowolo.benchmark.generationtype.repository;

import com.olowolo.benchmark.generationtype.domain.SequenceOptimizerUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author olOwOlo
 */
public interface SequenceOptimizerUserRepository extends
    JpaRepository<SequenceOptimizerUser, Integer> {

}
