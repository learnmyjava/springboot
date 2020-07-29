package test;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class RequestEntity {
    @NotNull(message = "表名不能为空")
    @Length(max = 30,message="表名最长长度30")
    private String  tableName;

    private String tableComment;

    @NotNull(message = "字段信息集合不能为空")
    @Size(min = 1, message = "至少有一个字段信息")
    @Valid
    List<ColumnInfo> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", columns=" + columns +
                '}';
    }
}
