package test;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class ColumnInfo {
    @NotEmpty(message = "字段名称不能为空")
    @Length(min = 1, max = 30, message = "字段名称最长不能大于30个字符")
    private String columnName;
    private String columnComment;

    @NotEmpty(message = "字段类型不能为空")
    private String columnType;

    private Integer columnLength;
    private Integer precisionLength;
    private Boolean isPrimaryKey;
    private Boolean isNotNull;
    private String defaultValue;
    private Boolean isIncrement;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Integer getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(Integer columnLength) {
        this.columnLength = columnLength;
    }

    public Integer getPrecisionLength() {
        return precisionLength;
    }

    public void setPrecisionLength(Integer precisionLength) {
        this.precisionLength = precisionLength;
    }

    public Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(Boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public Boolean getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(Boolean notNul) {
        isNotNull = notNul;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getIsIncrement() {
        return isIncrement;
    }

    public void setIsIncrement(Boolean increment) {
        isIncrement = increment;
    }

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnName='" + columnName + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnLength=" + columnLength +
                ", precisionLength=" + precisionLength +
                ", isPrimaryKey=" + isPrimaryKey +
                ", isNotNull=" + isNotNull +
                ", defaultValue='" + defaultValue + '\'' +
                ", isIncrement=" + isIncrement +
                '}';
    }
}
