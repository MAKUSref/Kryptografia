package GUI;

public enum FileStatuses {
    NO_FILE_CHOSEN("No file chosen"),
    FILE_CHOSEN("File chosen"),
    PRIVATE_KEY_REQUIRED("Private key required!"),
    PUBLIC_KEY_INCORRECT("Public key is incorrect!"),
    ENCODED("File encoded"),
    DECODED("File decoded");

    private final String communicate;

    private FileStatuses(String str) {
        communicate = str;
    }

    public String getMessage() {
        return this.communicate;
    }
}
