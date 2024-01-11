package com.xiii.lanchat.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXController {
    @FXML
    private Label doNothing;

    @FXML
    protected void doNothing() {
        doNothing.setText("");
    }
}