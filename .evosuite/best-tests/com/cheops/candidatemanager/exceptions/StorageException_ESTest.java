/*
 * This file was automatically generated by EvoSuite
 * Thu Oct 25 12:49:07 GMT 2018
 */

package com.cheops.candidatemanager.exceptions;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cheops.candidatemanager.exceptions.StorageException;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class StorageException_ESTest extends StorageException_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      StorageException storageException0 = new StorageException((String) null);
      StorageException storageException1 = new StorageException((String) null, storageException0);
      assertFalse(storageException1.equals((Object)storageException0));
  }
}