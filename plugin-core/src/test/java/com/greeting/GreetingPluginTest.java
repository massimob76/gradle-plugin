package com.greeting;


import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GreetingPluginTest {

    @Test
    void greetingTest(){
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("com.greeting");

        assertTrue(project.getPluginManager().hasPlugin("com.greeting"));

        assertNotNull(project.getTasks().getByName("hello"));
    }

}