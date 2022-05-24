package GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.RSA;

public class Controller {

    @FXML
    private TextArea textAreaDecodedMsg;
    @FXML
    private TextArea textAreaEncodedMsg;
    @FXML
    private TextArea textAreaPrivateKey;
    @FXML
    private TextArea textAreaPublicKey;

    @FXML
    public void initialize() {
        init();
    }

    public void handleExit() {
        Platform.exit();
    }

    public void handleClearPublicKey() {
        textAreaPublicKey.setText("");
    }

    public void handleClearPrivateKey() {
        textAreaPrivateKey.setText("");
    }

    public void handleClearMsg() {
        textAreaDecodedMsg.setText("");
    }

    public void handleClearEncodedMsg() {
        textAreaEncodedMsg.setText("");
    }

    public void handleClearAll() {
        handleClearPublicKey();
        handleClearPrivateKey();
        handleClearMsg();
        handleClearEncodedMsg();
    }

    public void handleEncodeMsg() {
        String msg = textAreaDecodedMsg.getText();
        String publicKey = textAreaPublicKey.getText();

        if (msg == null || msg.equals("")) {
            return;
        }

        if (publicKey.equals("")) {
            RSA rsa = new RSA(msg);
            rsa.encode();

            textAreaPrivateKey.setText(rsa.getPrivateKey());
            textAreaPublicKey.setText(rsa.getPublicKey());
            textAreaEncodedMsg.setText(rsa.getEncodedMsg());
        } else {
            String decodedMsg = RSA.encode(msg, publicKey);
            textAreaEncodedMsg.setText(decodedMsg);
        }

    }

    public void handleDecodeMsg() {
        System.out.println("Decoding...");

        String[] encodedMsg = textAreaEncodedMsg.getText().split("\n");
        String privateKey = textAreaPrivateKey.getText();

        if (encodedMsg.length == 0) {
            System.out.println("No message to decode!");
            return;
        }

        if (privateKey.equals("") || privateKey.split(":").length != 2) {
            System.out.println("Wrong Private Key!");
            return;
        }

        String msg = RSA.decode(encodedMsg, privateKey);
        textAreaDecodedMsg.setText(msg);
    }

    //  private
    private void init() {

    }

}





