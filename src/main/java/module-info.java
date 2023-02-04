module com.bednara.abschlussprojekt {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires jbcrypt;
	requires javafaker;
	requires org.controlsfx.controls;
	requires transitive MaterialFX;

	exports com.bednara.controllers to javafx.fxml;

    opens com.bednara.application to javafx.graphics, javafx.base;
	opens com.bednara.controllers to javafx.fxml;

}
