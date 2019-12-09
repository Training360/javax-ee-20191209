package empapp;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoIT {

    @Inject
    private EmployeeDao employeeDao;

    @Resource(lookup = "java:/jdbc/EmployeeDS")
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    @Deployment
    public static WebArchive createDeployment() {
        var deployment = ShrinkWrap
                .create(WebArchive.class)
                .addClasses(Employee.class, EmployeeDao.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsLibraries(Maven.configureResolver().loadPomFromFile("pom.xml")
                .resolve("org.assertj:assertj-core").withoutTransitivity().asSingleFile());
        return deployment;
    }

//     @Before
     public void empty() throws SQLException{
         try (
                 var conn = dataSource.getConnection();
                 var stmt = conn.prepareStatement("delete from employee")
         ) {
             var count = stmt.executeUpdate();
             System.out.println("Deleted: " + count);
         }
     }

    @Test
    public void testSaveThenList() {
        employeeDao.insertEmployee(new Employee("John Doe"));
        employeeDao.insertEmployee(new Employee("Jack Doe"));

        var names = employeeDao.listEmployees().stream().map(Employee::getName).collect(Collectors.toList());
        assertEquals(List.of("John Doe", "Jack Doe"), names);
        //assertThat(names).contains("John Doe");
    }

    @Test
    @Transactional
    public void testSaveThenList2()  {
        entityManager.createQuery("delete from Employee").executeUpdate();

        List<Employee> employees = employeeDao.listEmployees();
        employees.forEach(System.out::println);
    }

}
