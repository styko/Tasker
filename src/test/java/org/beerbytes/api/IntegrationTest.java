package org.beerbytes.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.beerbytes.TaskerApplication;
import org.beerbytes.TaskerConfiguration;
import org.beerbytes.core.Task;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

//TODO
public class IntegrationTest {
	
	private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("config.yml");

    @ClassRule
    public static final DropwizardAppRule<TaskerConfiguration> RULE = new DropwizardAppRule<>(
    		TaskerApplication.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));

    @BeforeClass
    public static void migrateDb() throws Exception {
        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

   /*@Test
    public void testHelloWorld() throws Exception {
        final Task tasks =  RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/task-list")
                .request()
                .get(Task.class);
        assertThat(tasks.getInfo()).isEqualTo("zzz");
    }
*/
   /* @Test
    public void testPostPerson() throws Exception {
        final Person person = new Person("Dr. IntegrationTest", "Chief Wizard");
        final Person newPerson = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/people")
                .request()
                .post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Person.class);
        assertThat(newPerson.getId()).isNotNull();
        assertThat(newPerson.getFullName()).isEqualTo(person.getFullName());
        assertThat(newPerson.getJobTitle()).isEqualTo(person.getJobTitle());
    }*/
}
