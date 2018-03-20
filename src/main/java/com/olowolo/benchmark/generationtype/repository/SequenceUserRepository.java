package com.olowolo.benchmark.generationtype.repository;

import com.olowolo.benchmark.generationtype.domain.SequenceUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author olOwOlo
 */
public interface SequenceUserRepository extends JpaRepository<SequenceUser, Integer> {

}
