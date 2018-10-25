/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Thu Oct 25 09:22:10 GMT 2018
 */

package com.cheops.candidatemanager.services.impl;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

import static org.evosuite.shaded.org.mockito.Mockito.*;
@EvoSuiteClassExclude
public class CandidateService_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.cheops.candidatemanager.services.impl.CandidateService"; 
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
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(CandidateService_ESTest_scaffolding.class.getClassLoader() ,
      "com.mysql.jdbc.JDBC4CallableStatement",
      "com.cheops.candidatemanager.repositories.CandidateRepository",
      "com.mysql.jdbc.StatementInterceptorV2",
      "com.mysql.jdbc.ConnectionPropertiesImpl$ConnectionProperty",
      "com.cheops.candidatemanager.models.Address",
      "org.springframework.beans.factory.annotation.Autowired",
      "com.cheops.candidatemanager.models.Candidate",
      "com.mysql.jdbc.JDBC4ServerPreparedStatement",
      "org.springframework.format.annotation.DateTimeFormat$ISO",
      "com.mysql.jdbc.Extension",
      "com.mysql.jdbc.AbandonedConnectionCleanupThread$1",
      "com.mysql.jdbc.ConnectionPropertiesImpl$LongConnectionProperty",
      "org.hsqldb.jdbc.JDBCDriver",
      "com.mysql.jdbc.JDBC42PreparedStatement",
      "com.mysql.jdbc.profiler.ProfilerEvent",
      "com.cheops.candidatemanager.models.Skills",
      "com.mysql.jdbc.StatementImpl",
      "org.hsqldb.jdbc.JDBCDriver$1",
      "com.mysql.jdbc.JDBC4ClientInfoProvider",
      "com.mysql.jdbc.CachedResultSetMetaData",
      "com.mysql.jdbc.ConnectionFeatureNotAvailableException",
      "com.mysql.jdbc.ServerPreparedStatement$BindValue",
      "com.mysql.jdbc.CallableStatement$CallableStatementParam",
      "com.mysql.jdbc.MysqlIO",
      "com.cheops.candidatemanager.services.ISkillService",
      "com.mysql.jdbc.Connection",
      "com.mysql.jdbc.NotUpdatable",
      "com.mysql.jdbc.StringUtils$SearchMode",
      "com.mysql.jdbc.OperationNotSupportedException",
      "com.mysql.jdbc.ConnectionPropertiesImpl",
      "com.mysql.jdbc.SingleByteCharsetConverter",
      "com.mysql.jdbc.PingTarget",
      "org.springframework.boot.test.context.FilteredClassLoader$PackageFilter",
      "com.mysql.jdbc.StatementImpl$CancelTask$1",
      "org.springframework.core.io.InputStreamSource",
      "com.mysql.jdbc.SocketFactory",
      "com.mysql.jdbc.JDBC42ResultSet",
      "com.mysql.jdbc.StatementImpl$CancelTask",
      "com.mysql.jdbc.PacketTooBigException",
      "com.mysql.jdbc.log.StandardLogger",
      "com.mysql.jdbc.authentication.CachingSha2PasswordPlugin",
      "com.mysql.jdbc.ConnectionImpl$3",
      "com.mysql.jdbc.ConnectionImpl$2",
      "com.mysql.jdbc.ConnectionImpl$5",
      "com.mysql.jdbc.JDBC4MySQLConnection",
      "com.mysql.jdbc.ConnectionImpl$7",
      "com.mysql.jdbc.ConnectionImpl$6",
      "com.mysql.jdbc.ConnectionImpl$9",
      "com.mysql.jdbc.ConnectionImpl$8",
      "com.mysql.jdbc.log.Log",
      "org.springframework.data.repository.NoRepositoryBean",
      "com.mysql.jdbc.StringUtils",
      "com.mysql.jdbc.ConnectionPropertiesImpl$MemorySizeConnectionProperty",
      "org.springframework.stereotype.Service",
      "com.mysql.jdbc.ResultSetImpl",
      "com.mysql.jdbc.exceptions.MySQLTransientException",
      "org.springframework.stereotype.Indexed",
      "com.mysql.jdbc.MySQLConnection",
      "com.mysql.jdbc.ConnectionPropertiesImpl$BooleanConnectionProperty",
      "com.mysql.jdbc.AuthenticationPlugin",
      "com.mysql.jdbc.UpdatableResultSet",
      "com.mysql.jdbc.Wrapper",
      "org.springframework.boot.test.context.FilteredClassLoader",
      "com.mysql.jdbc.Statement",
      "com.mysql.jdbc.Buffer",
      "com.mysql.jdbc.ConnectionImpl$10",
      "com.mysql.jdbc.JDBC4Connection",
      "com.cheops.candidatemanager.services.ICandidateService",
      "com.mysql.jdbc.ParameterBindings",
      "com.mysql.jdbc.JDBC4ResultSet",
      "com.mysql.jdbc.BufferRow",
      "com.mysql.jdbc.JDBC4MultiHostMySQLConnection",
      "com.mysql.jdbc.IterateBlock",
      "com.mysql.jdbc.Util$RandStructcture",
      "com.cheops.candidatemanager.repositories.AddressRepository",
      "com.mysql.jdbc.MultiHostConnectionProxy",
      "com.mysql.jdbc.exceptions.MySQLStatementCancelledException",
      "com.mysql.jdbc.PreparedStatement",
      "com.mysql.jdbc.exceptions.MySQLTimeoutException",
      "com.mysql.jdbc.util.ReadAheadInputStream",
      "com.mysql.jdbc.util.LRUCache",
      "com.mysql.jdbc.Util",
      "com.mysql.fabric.jdbc.FabricMySQLDriver",
      "org.springframework.format.annotation.DateTimeFormat",
      "com.mysql.jdbc.MysqlDataTruncation",
      "com.mysql.jdbc.CacheAdapter",
      "com.mysql.jdbc.AbandonedConnectionCleanupThread",
      "com.mysql.jdbc.profiler.ProfilerEventHandler",
      "com.mysql.jdbc.Field",
      "org.springframework.data.repository.Repository",
      "com.mysql.jdbc.PreparedStatement$BatchVisitor",
      "com.mysql.jdbc.CommunicationsException",
      "com.mysql.jdbc.ConnectionPropertiesImpl$StringConnectionProperty",
      "com.mysql.jdbc.ConnectionProperties",
      "com.cheops.candidatemanager.repositories.ApplicationProcessRepository",
      "org.h2.Driver",
      "com.mysql.jdbc.Driver",
      "com.mysql.jdbc.CompressedInputStream",
      "com.mysql.jdbc.ServerPreparedStatement",
      "com.mysql.jdbc.ResultSetRow",
      "com.cheops.candidatemanager.models.ApplicationProcess",
      "com.mysql.jdbc.ConnectionImpl",
      "com.mysql.jdbc.log.NullLogger",
      "com.mysql.jdbc.MultiHostMySQLConnection",
      "com.mysql.jdbc.JDBC42CallableStatement",
      "org.springframework.boot.test.context.FilteredClassLoader$ClassFilter",
      "com.mysql.jdbc.exceptions.MySQLNonTransientException",
      "com.cheops.candidatemanager.repositories.SkillsRepository",
      "com.mysql.jdbc.JDBC4UpdatableResultSet",
      "com.mysql.jdbc.JDBC4PreparedStatement",
      "com.mysql.jdbc.MysqlSavepoint",
      "com.mysql.jdbc.CallableStatement",
      "com.mysql.jdbc.JDBC42ServerPreparedStatement",
      "org.springframework.data.repository.CrudRepository",
      "com.cheops.candidatemanager.services.impl.SkillsService",
      "com.mysql.jdbc.RowData",
      "com.mysql.jdbc.JDBC42UpdatableResultSet",
      "com.mysql.jdbc.PreparedStatement$ParseInfo",
      "com.cheops.candidatemanager.services.impl.CandidateService",
      "com.cheops.candidatemanager.models.CandidateSearchResolver",
      "com.cheops.candidatemanager.services.IApplicationProcessService",
      "org.h2.JdbcDriverBackwardsCompat",
      "com.mysql.jdbc.Messages",
      "com.mysql.jdbc.StreamingNotifiable",
      "com.mysql.jdbc.NetworkResources",
      "com.mysql.jdbc.ResultSetInternalMethods",
      "org.springframework.data.util.Lazy",
      "com.mysql.jdbc.CallableStatement$CallableStatementParamInfo",
      "com.cheops.candidatemanager.services.impl.ApplicationProcessService",
      "com.mysql.jdbc.ConnectionPropertiesImpl$IntegerConnectionProperty",
      "org.springframework.stereotype.Component",
      "com.mysql.jdbc.NonRegisteringDriver",
      "com.mysql.jdbc.ByteArrayRow",
      "org.springframework.web.multipart.MultipartFile",
      "com.mysql.jdbc.ExceptionInterceptor",
      "org.springframework.util.Assert",
      "com.mysql.jdbc.authentication.Sha256PasswordPlugin"
    );
  } 
  private static void initMocksToAvoidTimeoutsInTheTests() throws ClassNotFoundException { 
    mock(Class.forName("com.cheops.candidatemanager.repositories.AddressRepository", false, CandidateService_ESTest_scaffolding.class.getClassLoader()));
    mock(Class.forName("com.cheops.candidatemanager.repositories.ApplicationProcessRepository", false, CandidateService_ESTest_scaffolding.class.getClassLoader()));
    mock(Class.forName("com.cheops.candidatemanager.repositories.CandidateRepository", false, CandidateService_ESTest_scaffolding.class.getClassLoader()));
    mock(Class.forName("com.cheops.candidatemanager.repositories.SkillsRepository", false, CandidateService_ESTest_scaffolding.class.getClassLoader()));
    mock(Class.forName("java.util.function.Function", false, CandidateService_ESTest_scaffolding.class.getClassLoader()));
  }

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(CandidateService_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.cheops.candidatemanager.services.impl.CandidateService",
      "org.springframework.format.annotation.DateTimeFormat$ISO",
      "com.cheops.candidatemanager.services.impl.ApplicationProcessService",
      "com.cheops.candidatemanager.services.impl.SkillsService",
      "com.cheops.candidatemanager.models.Candidate",
      "com.cheops.candidatemanager.models.Address",
      "com.cheops.candidatemanager.models.Skills",
      "com.cheops.candidatemanager.models.ApplicationProcess",
      "com.cheops.candidatemanager.models.CandidateSearchResolver",
      "com.mysql.jdbc.AbandonedConnectionCleanupThread$1",
      "com.mysql.jdbc.AbandonedConnectionCleanupThread",
      "com.mysql.jdbc.NonRegisteringDriver",
      "com.mysql.jdbc.Driver",
      "com.mysql.fabric.jdbc.FabricMySQLDriver",
      "org.h2.Driver",
      "org.hsqldb.jdbc.JDBCDriver",
      "org.springframework.data.util.Lazy",
      "org.springframework.util.Assert"
    );
  }
}
