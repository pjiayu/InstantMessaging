module com.wellread4man.instantmessaging {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.wellread4man.instantmessaging to javafx.fxml;
    exports com.wellread4man.instantmessaging;
}