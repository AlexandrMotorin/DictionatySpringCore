package org.example;

import org.example.Config.MyConfig;
import org.example.View.View;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        View view = context.getBean("viewBean",View.class);
        view.start();

        context.close();
    }
}
