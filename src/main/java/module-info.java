module com.example.project_2005033 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.project_2005033 to javafx.fxml;
    exports com.example.project_2005033;
}