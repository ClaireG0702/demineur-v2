module com.claireg.demineur {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.claireg.demineur to javafx.fxml;
    exports com.claireg.demineur;
}
