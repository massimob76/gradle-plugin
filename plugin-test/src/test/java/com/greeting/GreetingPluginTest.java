package com.greeting;

import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GreetingPluginTest {

    private TemporaryFolder testProjectDir = new TemporaryFolder();
    private File buildFile;

    @BeforeEach
    void setup() throws IOException {
        testProjectDir.create();
//        buildFile = testProjectDir.newFile("build-gradle");
    }

    @AfterEach
    void cleanUp() {
        testProjectDir.delete();
    }

    @Test
    void testHelloWorldTask() throws IOException, URISyntaxException {

        copyResourceToTestProjectDir("build-gradle");

        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("helloWorld")
                .build();

        assertTrue(result.getOutput().contains("Hello world!"));
        assertEquals(SUCCESS, result.task(":helloWorld").getOutcome());
    }

    private void copyResourceToTestProjectDir(String resourceName) throws URISyntaxException, IOException {
        Path source = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
        Path destination = Paths.get(testProjectDir.getRoot().toString(), "build.gradle");
        Files.copy(source, destination);
    }

}
