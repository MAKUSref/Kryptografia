package main;

import java.io.Serializable;

public class SerializableData implements Serializable {
    private BigInt[] fileData;
    private String filename;
    private String fileExtension;

    public SerializableData(BigInt[] fileData, String filename, String fileExtension) {
        this.filename = filename;
        this.fileExtension = fileExtension;

        if (fileData.length == 0) {
            throw new NullPointerException("Zero length array!");
        }

        this.fileData = fileData;
    }

    public BigInt[] getFileData() {
        return fileData;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
