package com.greeting;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;

public class GreetingTask extends DefaultTask {

    private final GreetingPluginExtension extension;

    @Inject
    public GreetingTask(GreetingPluginExtension extension) {
        this.extension = extension;
    }

    @TaskAction
    public void greet() throws Exception {
        System.out.print(extension.getMessage());
    }

}
