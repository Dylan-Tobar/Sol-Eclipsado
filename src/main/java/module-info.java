module com.example.soleclipsado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.soleclipsado to javafx.fxml;
    opens com.example.soleclipsado.Controllers to javafx.fxml;
    opens com.example.soleclipsado.Views to javafx.fxml;
    opens com.example.soleclipsado.Models to javafx.fxml;

    exports com.example.soleclipsado;
}