/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Tue Oct 16 13:00:12 GMT 2018
 */

package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

import static org.evosuite.shaded.org.mockito.Mockito.*;
@EvoSuiteClassExclude
public class SalaryPackageService_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.tv.tutorials.coolestuffvoorcheops.services.impl.SalaryPackageService"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    try { initMocksToAvoidTimeoutsInTheTests(); } catch(ClassNotFoundException e) {} 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("file.encoding", "Cp1252"); 
    java.lang.System.setProperty("java.awt.headless", "true"); 
    java.lang.System.setProperty("java.io.tmpdir", "C:\\Users\\THOMAS~1.VER\\AppData\\Local\\Temp\\"); 
    java.lang.System.setProperty("user.country", "US"); 
    java.lang.System.setProperty("user.dir", "C:\\Users\\thomas.verhulst\\Documents\\workspace-sts-3.9.5.RELEASE\\coolestuffvoorcheops"); 
    java.lang.System.setProperty("user.home", "C:\\Users\\thomas.verhulst"); 
    java.lang.System.setProperty("user.language", "en"); 
    java.lang.System.setProperty("user.name", "Thomas.Verhulst"); 
    java.lang.System.setProperty("user.timezone", "Europe/Paris"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(SalaryPackageService_ESTest_scaffolding.class.getClassLoader() ,
      "org.springframework.stereotype.Component",
      "org.springframework.stereotype.Service",
      "org.springframework.data.repository.Repository",
      "com.tv.tutorials.coolestuffvoorcheops.repositories.SalaryPackageRepository",
      "org.springframework.beans.factory.annotation.Autowired",
      "com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage",
      "com.tv.tutorials.coolestuffvoorcheops.services.impl.SalaryPackageService",
      "org.springframework.data.repository.CrudRepository",
      "com.tv.tutorials.coolestuffvoorcheops.services.ISalaryPackageService"
    );
  } 
  private static void initMocksToAvoidTimeoutsInTheTests() throws ClassNotFoundException { 
    mock(Class.forName("com.tv.tutorials.coolestuffvoorcheops.repositories.SalaryPackageRepository", false, SalaryPackageService_ESTest_scaffolding.class.getClassLoader()));
  }

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(SalaryPackageService_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.tv.tutorials.coolestuffvoorcheops.services.impl.SalaryPackageService",
      "com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage",
      "org.springframework.boot.test.context.FilteredClassLoader$ClassFilter",
      "com.mysql.jdbc.AbandonedConnectionCleanupThread$1",
      "com.mysql.jdbc.AbandonedConnectionCleanupThread",
      "com.mysql.jdbc.NonRegisteringDriver",
      "com.mysql.jdbc.Driver",
      "com.mysql.fabric.jdbc.FabricMySQLDriver",
      "org.h2.Driver",
      "org.hsqldb.jdbc.JDBCDriver"
    );
  }
}
