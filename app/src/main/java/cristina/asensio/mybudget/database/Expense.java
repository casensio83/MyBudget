package cristina.asensio.mybudget.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    @ColumnInfo(name = "fecha")
    private String mDate;

    @NonNull
    @ColumnInfo(name = "amount")
    private String mAmount;

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    public Expense(@NonNull String mDate, @NonNull String mAmount, @NonNull String mTitle, @NonNull String mDescription) {
        this.mDate = mDate;
        this.mAmount = mAmount;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @NonNull
    public String getDate() {
        return mDate;
    }

    @NonNull
    public String getAmount() {
        return mAmount;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }
}
