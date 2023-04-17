package cz.pf;

public enum ActionWithArray {
    PRINT_ARRAY(1),
    ADD_NUMBER(2),
    REMOVE_NUMBER(3),
    LARGEST_NUMBER(4),
    SMALLEST_NUMBER(5),
    SUM_NUMBERS(6),
    DELETE_ARRAY(7),
    REGENERATE_ARRAY(8),
    RETURN_TO_MENU(9);

    final int actionId;
    ActionWithArray(int actionId) {
        this.actionId = actionId;
    }
}
