package com.olowolo.benchmark.generationtype.repository;

import com.olowolo.benchmark.generationtype.domain.TableUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author olOwOlo
 */
public interface TableUserRepository extends JpaRepository<TableUser, Integer> {

}
