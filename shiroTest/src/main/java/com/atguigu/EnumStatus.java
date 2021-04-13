package com.atguigu;

public enum EnumStatus {
    INIT("0"),
    READY("1"),
    PROCESS("2"),
    FINISH("3"),
    ;

    private String code;
    private EnumStatus(String code) {
       this.code = code;
    }

    public String getValue() {
        return code;
    }
}
