package chain.enums;

public enum ApplyType {
    SENIOR("高校"), ADULT("成人");
    private String applyType;

    ApplyType(String applyType) {
        this.applyType = applyType;
    }
    public String val(){
        return this.applyType;
    }
}
