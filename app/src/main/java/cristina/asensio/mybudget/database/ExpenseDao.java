package cristina.asensio.mybudget.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Query("SELECT * from expense_table")
    LiveData<List<Expense>> getAlphabetizedExpenses();

    //TODO check why when closing app the data is not saved
    @Insert
    void insert(Expense expense);

    @Query("DELETE FROM expense_table")
    void deleteAll();

    @Delete
    void delete(Expense expense);

}
