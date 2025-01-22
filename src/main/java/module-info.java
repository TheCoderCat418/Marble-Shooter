module com.example.template {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    opens com.example.template to javafx.fxml;
    exports com.example.template;
}