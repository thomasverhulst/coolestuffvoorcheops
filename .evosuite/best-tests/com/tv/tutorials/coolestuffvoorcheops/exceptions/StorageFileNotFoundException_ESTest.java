/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 16 13:58:11 GMT 2018
 */

package com.tv.tutorials.coolestuffvoorcheops.exceptions;

import org.junit.Test;
import static org.junit.Assert.*;
import com.tv.tutorials.coolestuffvoorcheops.exceptions.StorageFileNotFoundException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.lang.MockThrowable;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class StorageFileNotFoundException_ESTest extends StorageFileNotFoundException_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      MockThrowable mockThrowable0 = new MockThrowable(";o^*i]WcF#[i");
      StorageFileNotFoundException storageFileNotFoundException0 = new StorageFileNotFoundException(";o^*i]WcF#[i", mockThrowable0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      StorageFileNotFoundException storageFileNotFoundException0 = new StorageFileNotFoundException(";o^*i]WcF#[i");
  }
}
