public enum Color {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    YELLOW("\u001B[33m");
    private final String code;
    Color(String code){
        this.code = code;
    }
    public String getColor(String text){
        return code + text + "\u001B[0m";
    }
}
