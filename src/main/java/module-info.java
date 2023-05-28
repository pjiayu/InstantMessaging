module com.wellread4man.instantmessaging {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fastjson;


    opens com.wellread4man.instantmessaging to javafx.fxml;
    opens Pojo to fastjson;
    exports com.wellread4man.instantmessaging;
    exports Pojo;
}