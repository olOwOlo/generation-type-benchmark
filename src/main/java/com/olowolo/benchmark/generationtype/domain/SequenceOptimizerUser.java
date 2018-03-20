package com.olowolo.benchmark.generationtype.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author olOwOlo
 */
@Table
@Entity
@Data
public class SequenceOptimizerUser implements GenericUser {

  @Id
  @GeneratedValue(generator = "sou_g", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "sou_g", sequenceName = "sou_sequence")
  private Integer id;

  private String firstName;
  private String lastName;
}
