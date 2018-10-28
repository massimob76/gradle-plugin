package com.greeting;

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingPluginTest {

    private final TemporaryFolder testProjectDir = new TemporaryFolder();

    @BeforeEach
    void setUp() throws IOException {
        testProjectDir.create();
    }

    @AfterEach
    void cleanUp() {
        testProjectDir.delete();
    }

    @Test
    void greetingTest() throws Exception {

        copyResourceToTestProjectDir("build-gradle");

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withPluginClasspath()
                .withArguments("hello")
                .build();

        assertEquals(SUCCESS, result.task(":hello").getOutcome());
        assertThat(result.getOutput(), containsString("Hello from GreetingPlugin"));

    }

    @Test
    void greetingCustomMessageTest() throws Exception {

        copyResourceToTestProjectDir("build-gradle-custom-name");

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withPluginClasspath()
                .withArguments("hello")
                .build();

        assertEquals(SUCCESS, result.task(":hello").getOutcome());
        assertThat(result.getOutput(), containsString("Hello from Massimo"));
    }

    private void copyResourceToTestProjectDir(String resourceName) throws URISyntaxException, IOException {
        Path source = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
        Path destination = Paths.get(testProjectDir.getRoot().toString(), "build.gradle");
        Files.copy(source, destination);
    }
}
