package com.github.desktop.demo.app;

import com.github.desktop.demo.controller.DashboardView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver fxWeaver;

    @Autowired
    public PrimaryStageInitializer(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        log.debug("Stage Ready Event");
        Stage stage = event.getStage();
        Scene scene = new Scene(fxWeaver.loadView(DashboardView.class), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
