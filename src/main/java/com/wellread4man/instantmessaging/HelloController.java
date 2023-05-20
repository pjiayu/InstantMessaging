package com.wellread4man.instantmessaging;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.AcceptLoginServerImpl;
import util.RollBackImpl;
import util.Utils;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        Utils.init();
        AcceptLoginServerImpl AcceptLogin = new AcceptLoginServerImpl(new RollBackImpl());
        Thread thread = new Thread(AcceptLogin);
        thread.start();
    }
}