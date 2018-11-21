package com.tas.androiddemo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_2 {
  @Test
  public void f() {
  }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Test 2 Started");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Test 2 Finished");
  }

}
