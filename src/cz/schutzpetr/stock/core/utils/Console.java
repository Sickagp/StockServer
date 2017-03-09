package cz.schutzpetr.stock.core.utils;

import cz.schutzpetr.stock.core.utils.iface.PrinterImpl;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Petr Schutz on 05.03.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class Console extends OutputStream implements PrinterImpl {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private TextArea console;

    public Console(TextArea console) {
        this.console = console;
    }

    private void appendText(String valueOf) {
        Platform.runLater(() -> console.appendText(valueOf));
    }

    public void write(int b) throws IOException {
        appendText(String.valueOf((char)b));
    }

    @Override
    public void print(String message) {
        appendText(message);
    }

    @Override
    public void println(String message) {
        appendText(String.format("%s%s", message, NEW_LINE));
    }
}