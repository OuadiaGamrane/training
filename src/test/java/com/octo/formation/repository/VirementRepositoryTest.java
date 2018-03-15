package com.octo.formation.repository;

import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VirementRepositoryTest {

  @Autowired
  private VirementRepository virementRepository;

  @Test
  public void findOne() {
  }

  @Test
  public void findAll() {

  }

  @Test
  public void save() {
  }

  @Test
  public void delete() {
  }
}