/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Thu Oct 25 12:52:09 GMT 2018
 */

package com.cheops.candidatemanager.controllers;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class OpenIdController_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.cheops.candidatemanager.controllers.OpenIdController"; 
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
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(OpenIdController_ESTest_scaffolding.class.getClassLoader() ,
      "com.cheops.candidatemanager.controllers.OpenIdController",
      "org.springframework.stereotype.Component",
      "org.springframework.security.core.context.ThreadLocalSecurityContextHolderStrategy",
      "org.springframework.security.core.context.SecurityContext",
      "org.springframework.security.core.context.SecurityContextImpl",
      "org.springframework.stereotype.Controller",
      "org.springframework.security.core.context.SecurityContextHolder",
      "org.springframework.security.core.Authentication",
      "org.springframework.security.core.context.SecurityContextHolderStrategy",
      "org.springframework.util.StringUtils"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(OpenIdController_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.cheops.candidatemanager.controllers.OpenIdController",
      "org.springframework.util.StringUtils",
      "org.springframework.security.core.context.ThreadLocalSecurityContextHolderStrategy",
      "org.springframework.security.core.context.SecurityContextHolder",
      "org.springframework.security.core.context.SecurityContextImpl",
      "org.springframework.web.bind.annotation.RequestMethod",
      "org.springframework.util.ConcurrentReferenceHashMap$ReferenceType",
      "org.springframework.util.ConcurrentReferenceHashMap",
      "org.springframework.util.Assert",
      "org.springframework.util.ConcurrentReferenceHashMap$Segment",
      "org.springframework.util.ConcurrentReferenceHashMap$ReferenceManager",
      "org.springframework.util.ReflectionUtils",
      "org.springframework.security.core.context.InheritableThreadLocalSecurityContextHolderStrategy",
      "org.springframework.util.ConcurrentReferenceHashMap$Task",
      "org.springframework.security.core.context.GlobalSecurityContextHolderStrategy",
      "org.springframework.util.ConcurrentReferenceHashMap$Entries",
      "org.springframework.util.ConcurrentReferenceHashMap$Segment$1",
      "org.springframework.util.ConcurrentReferenceHashMap$3",
      "org.springframework.util.ConcurrentReferenceHashMap$TaskOption",
      "org.springframework.util.ConcurrentReferenceHashMap$1",
      "org.springframework.util.ConcurrentReferenceHashMap$5",
      "org.springframework.util.ConcurrentReferenceHashMap$4",
      "org.springframework.util.ConcurrentReferenceHashMap$2"
    );
  }
}
