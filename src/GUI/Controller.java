package GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.BigInt;
import main.DataDao;
import main.RSA;
import main.SerializableData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Controller {
    String FILE_OUTFIX = "-encrypted";
    FileStatuses currentFileStatus;
    FileChooser fileChooser = new FileChooser();
    File currentFile;
    @FXML
    private TextArea textAreaDecodedMsg;
    @FXML
    private TextArea textAreaEncodedMsg;
    @FXML
    private TextArea textAreaPrivateKey;
    @FXML
    private TextArea textAreaPublicKey;
    @FXML
    private Label labelFileStatus;
    @FXML
    private Label labelFileName;
    @FXML
    private Button buttonEncodeFile;
    @FXML
    private Button buttonDecodeFile;
    @FXML
    private Button buttonSaveFile;
    @FXML
    private AnchorPane anchorPane;

    // setters
    public void setCurrentFileStatus(FileStatuses status) {
        currentFileStatus = status;
        labelFileStatus.setText(status.getMessage());
    }

    @FXML
    public void initialize() {
        setCurrentFileStatus(FileStatuses.NO_FILE_CHOSEN);
    }

    // handles
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
//        String msg = textAreaDecodedMsg.getText();
//        String publicKey = textAreaPublicKey.getText();
//
//        if (msg == null || msg.equals("")) {
//            return;
//        }
//
//        if (publicKey.equals("")) {
//            RSA rsa = new RSA(msg);
//            rsa.encode();
//
//            textAreaPrivateKey.setText(rsa.getPrivateKey());
//            textAreaPublicKey.setText(rsa.getPublicKey());
//            textAreaEncodedMsg.setText(rsa.getEncodedMsg());
//        } else {
//            String decodedMsg = RSA.encode(msg, publicKey);
//            textAreaEncodedMsg.setText(decodedMsg);
//        }

    }

    public void handleDecodeMsg() {
//        String[] encodedMsg = textAreaEncodedMsg.getText().split("\n");
//        String privateKey = textAreaPrivateKey.getText();
//
//        if (encodedMsg.length == 0) {
//            System.out.println("No message to decode!");
//            return;
//        }
//
//        if (privateKey.equals("") || privateKey.split(":").length != 2) {
//            System.out.println("Wrong Private Key!");
//            return;
//        }
//
//        String msg = RSA.decode(encodedMsg, privateKey);
//        textAreaDecodedMsg.setText(msg);
    }

    public void handleOpenFileToEncode() {
        Stage currentStage = (Stage) anchorPane.getScene().getWindow();
        currentFile = fileChooser.showOpenDialog(currentStage);
        labelFileName.setText(currentFile.getName());
        buttonEncodeFile.setDisable(false);
        buttonDecodeFile.setDisable(true);
        setCurrentFileStatus(FileStatuses.FILE_CHOSEN);
    }

    public void handleOpenFileToDecode() {
        Stage currentStage = (Stage) anchorPane.getScene().getWindow();
        currentFile = fileChooser.showOpenDialog(currentStage);
        labelFileName.setText(currentFile.getName());
        buttonEncodeFile.setDisable(true);
        buttonDecodeFile.setDisable(false);
        setCurrentFileStatus(FileStatuses.FILE_CHOSEN);
    }

    public void handleEncodeFile() {
//        String publicKey = textAreaPublicKey.getText();
//        boolean textAreaEmpty = publicKey.equals("");
//        boolean keyProper = publicKey.split(":").length == 2;
//
//        if (!keyProper && !textAreaEmpty) {
//            setCurrentFileStatus(FileStatuses.PUBLIC_KEY_INCORRECT);
//            return;
//        }
//
//        int len = (int) currentFile.length();
//        byte[] fileBytes = new byte[len];
//        int[] fileInts = new int[len];
//
//        try (FileInputStream fis = new FileInputStream(currentFile)) {
//            fis.read(fileBytes);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//
//        int i = 0;
//        for (byte row: fileBytes) {
//            fileInts[i] = ((int) row) & 0xff;
//            i++;
//        }
//
//        if (textAreaEmpty) {
//            RSA rsa = new RSA(fileInts);
//            rsa.encode();
//
//            textAreaPrivateKey.setText(rsa.getPrivateKey());
//            textAreaPublicKey.setText(rsa.getPublicKey());
//
//            String filename = currentFile.getName().split("\\.")[0];
//            String fileExtension = currentFile.getName().split("\\.")[1];
//
//
//            SerializableData data = new SerializableData(rsa.getEncodedBytes(), filename, fileExtension);
//            DataDao.write(filename + FILE_OUTFIX, data);
//
//        } else {
////            Encode Via Public key
//        }
//
//        System.out.println("Saved!");
    }

    public void handleDecodeFile() {
//        String privateKey = textAreaPrivateKey.getText();
//        if (privateKey.equals("") || privateKey.split(":").length != 2) {
//            System.out.println("Wrong Private Key!");
//            return;
//        }
//
//        String filename = currentFile.getName();
//        SerializableData data = DataDao.read(filename);
//        int[] decodedData = RSA.decode(data.getFileData(), privateKey);
//        byte[] decodedDataBytes = new byte[decodedData.length];
//
//        for (int i = 0; i < decodedData.length; i++) {
//            decodedDataBytes[i] = (byte) decodedData[i];
//        }
//
//        File f = new File("./", data.getFilename() + "." + data.getFileExtension());
//
//        try (FileOutputStream fos = new FileOutputStream(f)) {
//            fos.write(decodedDataBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("Decoded!");


//        for (int i: decodedData) {
//            System.out.println(i);
//        }
    }



    // public
}





