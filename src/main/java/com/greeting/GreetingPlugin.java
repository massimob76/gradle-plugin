package com.greeting;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class GreetingPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        GreetingPluginExtension extension = project.getExtensions().create("greeting", GreetingPluginExtension.class);

        Task customTask = project.task("hello").doLast(task -> System.out.println(extension.getMessage()));
        customTask.setDescription("hello world task");
        customTask.setGroup("my custom plugin");
    }
}
