package com.olowolo.benchmark.generationtype.repository;

import com.olowolo.benchmark.generationtype.domain.IdentityUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author olOwOlo
 */
public interface IdentityUserRepository extends JpaRepository<IdentityUser, Integer> {

}
