package org.demo.services;

public class DemoDataService {

}

/*
<persistence-unit name="CRM_PG" transaction-type="RESOURCE_LOCAL">
		<provider>org.hibernate.ejb.HibernatePersistence</provider>
		<properties>
			<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://192.168.10.68:5432/postgres"/>
			<property name="javax.persistence.jdbc.user" value="crmdemo"/>
			<property name="javax.persistence.jdbc.password" value="crmdemo"/>
			<property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
			
         	<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
         	<property name="hibernate.hbm2ddl.auto" value="none"/>
		</properties>
	</persistence-unit>
	
<persistence-unit name="ACCESS_LOCAL" transaction-type="RESOURCE_LOCAL">
	</persistence-unit>	
	
	<persistence-unit name="XLS_LOCAL" transaction-type="RESOURCE_LOCAL">
	</persistence-unit>	

*/

/*
 * TODO: Add hibernate profiles pom.xml
 * 
 * Runtime configuration: -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus/datanucleus-core-3.2.5.jar"=-api=JPA,org.office.xls
 * <property name="javax.persistence.jdbc.url" value="ooxml:file://E:/Professional/NextData/MDS-Demo-Data/OfficeDataSource/ExcelTestAccess.xlsx"/>
 * <property name="javax.persistence.jdbc.url" value="xls:file://E:/Professional/NextData/MDS-Demo-Data/OfficeDataSource/ExcelTestAccess.xlsx"/>
 * <property name="javax.persistence.jdbc.url" value="odf:file:tutorial.ods"/>
 * <property name="javax.persistence.jdbc.url" value="excel:file://E:/Professional/NextData/MDS-Demo-Data/OfficeDataSource/ExcelTestAccess.xls"/>
 * -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus4m4/datanucleus-core-4.0.0-m4.jar"=-api=JPA,org.office.xls
 * -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus/datanucleus-core-3.2.5.jar"=-api=JPA,org.office.xls
 * -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus-3.3.8/datanucleus-core-3.2.13.jar"=-api=JPA,org.office.xls
 */

/*
 * ACCESS:
 * 
 * http://www.javaxt.com/Tutorials/JDBC/How_to_Open_a_JDBC_Connection_to_Microsoft_Access
 * http://www.easysoft.com/applications/microsoft-access/jdbc-odbc.html
 * 
 * http://ucanaccess.sourceforge.net/site.html
<?xml version="1.0" encoding="utf-8"?>
<persistence>
    <persistence-unit name="traderMandate">
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.SQLServerDialect" />
            <property name="hibernate.connection.url" value="jdbc:ucanaccess://C:/MY.accdb;" />
            <property name="hibernate.connection.driver_class" value="net.ucanaccess.jdbc.UcanaccessDriver"/>
            <property name="hibernate.archive.autodetection" value="class" />
        </properties>
    </persistence-unit>
</persistence>    
 *
 * 
 * http://www.programmingforfuture.com/2011/06/how-to-use-ms-access-with-hibernate.html
 * 
 * http://javakafunda.blogspot.ro/2010/01/how-to-connect-hibernate-with-microsoft.html
 * 
 * 
 * 
 * http://java.dzone.com/articles/restify-your-sql-data?mz=62447-cloud
 * http://restsql.org/doc/Architecture.html
 * 
 * 
 * EXCEL:
 * http://datanucleus.blogspot.ro/2010/07/writing-data-to-excel-from-java-simple.html
 * http://www.datanucleus.org/products/datanucleus/jpa/guides/tutorial_excel.html
 * 
 */


/*
<properties>
	<property name="javax.persistence.jdbc.url" 
		value="jdbc:odbc:DSN=demo_access;DBQ=E:\Professional\NextData\MDS-Demo-Data\OfficeDataSource\MDSDemoAccessDB.accdb;DriverId=25;FIL=MS Access;MaxBufferSize=2048;PageTimeout=5;"/>
	<property name="javax.persistence.jdbc.driver" value="com.hxtt.sql.access.AccessDriver"/>				
 	<property name="hibernate.dialect" value="com.hxtt.support.hibernate.HxttAccessDialect"/>
 	<property name="hibernate.hbm2ddl.auto" value="create-drop"/>
</properties>
*/


/*
CustomClassLoader cstLoader = new CustomClassLoader();
Object emf = null;
Object em = null;
Object q = null;

Method method = cstLoader.loadClass("javax.persistence.Persistence").getMethod("createEntityManagerFactory", String.class);
emf = method.invoke(null, "XLS_LOCAL");
logger.info(">>>>>>>>>>> DEBUG emf = " + emf);
method = cstLoader.loadClass("javax.persistence.EntityManagerFactory").getMethod("createEntityManager", null);
em = method.invoke(emf, null);
logger.info(">>>>>>>>>>> DEBUG em = " + em);
method = cstLoader.loadClass("javax.persistence.EntityManager").getMethod("createQuery", String.class);
q =  method.invoke(em, "DELETE FROM AdvertisingExpense a");
method = cstLoader.loadClass("javax.persistence.Query").getMethod("executeUpdate", null);
method.invoke(q, null);
//method = cstLoader.loadClass(EntityManager.class.getName()).getMethod("persist", Object.class);
//method.invoke(em, expense);		

CustomClassLoader cstLoader = new CustomClassLoader();
Class entityClass = cstLoader.loadClass("org.office.xls.AdvertisingExpense");
Object expense = entityClass.newInstance();

entityClass.getMethod("setProductCode", Integer.class).invoke(expense, 1001);
entityClass.getMethod("setAdvExpCateg", String.class).invoke(expense, "media test");
entityClass.getMethod("setAdvExpAmount", Double.class).invoke(expense, 1000.0)
List<Object> exps = em.createQuery("SELECT a FROM AdvertisingExpense a").getResultList();
for(Object a: exps)
	logger.info(a.toString());	
*/