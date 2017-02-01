package com.todomvcse.helpers;

import java.util.ArrayList;
import java.util.List;

import static com.todomvcse.core.ConciseAPI.*;

public class Preconditions {

    private final List<String> taskNames;
    private final List<String> taskStatus;
    private final String filter;
    private final static String URL = "https://todomvc4tasj.herokuapp.com";

    private Preconditions(PreconditionBuilder builder) {

        this.taskNames = builder.taskNames;
        this.taskStatus = builder.taskStatus;
        this.filter = builder.filter;
    }

    public static PreconditionBuilder precondition() {
        return new PreconditionBuilder();
    }

    public void prepare() {

        if (!url().equals(filter)) {
            open(filter);
        }

        String queryToExecute = " ";

        for (int i = 0; i < taskNames.size(); i++) {

            queryToExecute += "{\"completed\":"
                    + taskStatus.get(i)
                    + ",\"title\":\""
                    + taskNames.get(i)
                    + "\"},";
        }

        queryToExecute = "localStorage.setItem('todos-troopjs','["
                + queryToExecute.substring(0, queryToExecute.length() - 1)
                + "]')";

        executeJavaScript(queryToExecute);
        executeJavaScript("location.reload()");
    }

    public static class PreconditionBuilder {

        private List<String> taskNames = new ArrayList<String>();
        private List<String> taskStatus = new ArrayList<String>();
        private String filter = URL;

        private void fillTasksList(String status, String... tasks) {

            for (String task : tasks) {

                this.taskStatus.add(status);
                this.taskNames.add(task);
            }
        }

        public PreconditionBuilder activeTasks(String... tasks) {

            fillTasksList("false", tasks);
            return this;
        }

        public PreconditionBuilder completedTasks(String... tasks) {

            fillTasksList("true", tasks);
            return this;
        }

        public PreconditionBuilder atAllFilter() {

            this.filter = URL;
            return this;
        }

        public PreconditionBuilder atActiveFilter() {

            this.filter = URL + "/#/active";
            return this;
        }

        public PreconditionBuilder atCompletedFilter() {

            this.filter = URL + "/#/completed";
            return this;
        }

        public void prepare() {
            build().prepare();
        }

        public Preconditions build() {
            return new Preconditions(this);
        }

    }
}

