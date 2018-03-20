package com.olowolo.benchmark.generationtype.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class TableUser implements GenericUser {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Integer id;

  private String firstName;
  private String lastName;
}
