package com.olowolo.benchmark.generationtype.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author olOwOlo
 */
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableOptimizerUser implements GenericUser {

  @Id
  @GeneratedValue(generator = "tou_g", strategy = GenerationType.TABLE)
  @TableGenerator(name = "tou_g")
  private Integer id;

  private String firstName;
  private String lastName;
}
