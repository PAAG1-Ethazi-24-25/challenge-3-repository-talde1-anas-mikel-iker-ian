module devworks {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens devworks to javafx.fxml;
    opens devworks.controller to javafx.fxml;
    opens devworks.model.base to javafx.fxml;

    exports devworks;
    exports devworks.controller;
    exports devworks.model;
    exports devworks.model.base;
}
