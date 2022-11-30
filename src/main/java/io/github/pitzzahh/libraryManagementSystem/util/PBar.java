package io.github.pitzzahh.libraryManagementSystem.util;

import static io.github.pitzzahh.libraryManagementSystem.LibraryManagementSystem.getLogger;
import javafx.scene.control.ProgressBar;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import java.util.Random;

public class PBar {

    /**
     * Creates a task for progress bar to run.
     * @param progressBar the progress bar.
     * @return the task.
     */
    public static Service<Void> showProgressBar(ProgressBar progressBar) {
        var service = new TaskService();
        progressBar.setVisible(true);
        getLogger().debug(progressBar.isVisible() ? "Progress bar is visible" : "Progress bar is not visible");
        progressBar.progressProperty().bind(service.progressProperty());
        return service;
    }

    /**
     * A task that runs in the background.
     */
    private static class TaskService extends Service<Void> {
        final Random random = new Random();
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(random.nextInt(10) + 1);
                        updateProgress(i, 100);
                    }
                    return null;
                }
            };
        }
    }
}
