package com.example.backtest.use_case.operation;

public enum OperationsEnum {

    COMPRA_A_VISTA(1, "Compra à Vista", "com.example.backtest.use_case.operation.OperationTransactionCompraVista"),
    COMPRA_PARCELADA(2, "Compra Parcelada", "com.example.backtest.use_case.operation.OperationTransactionCompraParcelada"),
    SAQUE(3, "Saque", "com.example.backtest.use_case.operation.OperationTransactionSaque"),
    PAGAMENTO(4, "Pagamento", "com.example.backtest.use_case.operation.OperationTransactionPagamento");

    private final int code;
    private final String description;

    private final String operationTransactionClass;

    OperationsEnum(int code, String description, String operationTransactionClass) {
        this.code = code;
        this.description = description;
        this.operationTransactionClass = operationTransactionClass;
    }

    public static OperationsEnum getByCode(int code) {
        for (OperationsEnum operation : values()) {
            if (operation.code == code) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Invalid Operations code: " + code);
    }

    public static OperationTransaction getOperatorClassByCode(int code) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (OperationsEnum operation : values()) {
            if (operation.code == code) {
                Class<?> clazz = Class.forName(operation.operationTransactionClass);
                OperationTransaction operationClass = (OperationTransaction) clazz.newInstance();
                return operationClass;
            }
        }
        throw new IllegalArgumentException("Invalid Operations code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
