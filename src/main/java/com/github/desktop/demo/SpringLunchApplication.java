package com.github.desktop.demo;

import com.github.desktop.demo.app.SpringJavaFXDemoApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLunchApplication {

    public static void main(String[] args) {
        Application.launch(SpringJavaFXDemoApplication.class, args);
    }
}
