# SOS-Test-Suite [![Build Status][travis-status]][travis] #

## How to use the test suite ##

Include the following artifacts in your `pom.xml`:

```xml
<dependency>
    <groupId>org.n52.sensorweb.sos</groupId>
    <artifactId>test-suite-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.n52.sensorweb.sos</groupId>
    <artifactId>test-suite-sos-20</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

The tests can be faciliated by implementing the class `ComplianceSuite`.
The method `#getTests()` defines the tests that sould be run with the
`RequestExecutor` provided by the method `#getExecutor()`.

The `RequestExecutor` provides methods to build and issue request to a
service instance. The instance can be external deployed, embedded with
Jetty or even a `HTTPServlet` instance that is feeded with mocked
requests and responses.

Additional dependencies and requirements should be included by using
[JUnit rules][junit-rules]. The following example uses a H2 database
as a `ExternalResource` (which also bootstraps the service) and issues
requests using the provided `MockHttpExecutor`. The tested service uses
different endpoints for bindings which are defined in the implemented
methods of `MockHttpExecutor`.

```java
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.n52.sos.service.SosService;
import org.n52.sos.service.it.*;
import org.n52.sos.service.it.v2.kvp.*;

@RunWith(ComplianceSuiteRunner.class)
public class KvpSuiteTest implements ComplianceSuite {
    private final H2Database datasource = new H2Database();
    private final RequestExecutor executor = new RequestExecutorImpl();

    @Rule public H2Database getDatasource() { return this.datasource; }
    @Override public RequestExecutor getExecutor() { return this.executor; }

    @Override
    public Class<?>[] getTests() {
        return new Class<?>[] {
            DeleteObservationTest.class,
            DeleteSensorTest.class,
            DescribeSensorTest.class,
            GetCapabilitiesTest.class,
            GetDataAvailabilityTest.class,
            GetFeatureOfInterestTest.class,
            GetObservationByIdTest.class,
            GetObservationTest.class,
            GetResultTemplateTest.class,
            GetResultTest.class
        };
    }

    private class RequestExecutorImpl extends MockHttpExecutor {
        RequestExecutorImpl() { super(SosService.class); }
        @Override public Client kvp() { return get("/kvp"); }
        @Override public Client pox() { return post("/pox"); }
        @Override public Client soap() { return post("/soap"); }
    }
}
```


## How to provide tests ##

Tests are instances of `AbstractComplianceSuiteTest`. The class provides methods
to obtain an `RequestExecutor`/`Client` to issue requests:

```java
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.n52.sos.service.it.v2.ExceptionMatchers.*;
import org.junit.Test;
import org.n52.sos.service.it.*;
import org.w3c.dom.Node;

public class GetObservationTest extends AbstractComplianceSuiteTest {

    @Test public void missingServiceParameter() {
        Node node = getExecutor().kvp()
                .query("request", "GetObservation")
                .query("version", "2.0.0")
                .response().asNode();
        assertThat(node, is(missingServiceParameterValueException()));
    }
}
```

[junit-rules]: https://github.com/junit-team/junit/wiki/Rules "JUnit Wiki"
[travis]: https://travis-ci.org/52North/SOS-Test-Suite "Travis CI"
[travis-status]: https://travis-ci.org/52North/SOS-Test-Suite.png "Build Status"