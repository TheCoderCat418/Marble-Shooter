module com.thecodercat418.marbleShooter {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    opens com.thecodercat418.marbleShooter to javafx.fxml;
    exports com.thecodercat418.marbleShooter;
}