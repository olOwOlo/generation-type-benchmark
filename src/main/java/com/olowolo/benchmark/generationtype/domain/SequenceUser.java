package com.olowolo.benchmark.generationtype.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author olOwOlo
 */
@Table
@Entity
@Data
public class SequenceUser implements GenericUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  private String firstName;
  private String lastName;
}
