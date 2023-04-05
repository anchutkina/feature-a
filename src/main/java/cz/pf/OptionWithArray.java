package cz.pf;

public enum OptionWithArray {
    CREATE_ARRAY(1),
    SELECT_ARRAY(2),
    LARGEST_SUM(3),
    SMALLEST_SUM(4),
    FINISH(5);

    final int optionId;
    OptionWithArray(int optionId) {
        this.optionId = optionId;
    }
}

