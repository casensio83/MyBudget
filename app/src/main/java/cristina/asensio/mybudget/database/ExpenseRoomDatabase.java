package cristina.asensio.mybudget.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Expense.class}, version = 1)
public abstract class ExpenseRoomDatabase extends RoomDatabase{

    public abstract ExpenseDao expenseDao();

    private static volatile ExpenseRoomDatabase INSTANCE;

    static ExpenseRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ExpenseRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpenseRoomDatabase.class, "expense_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ExpenseDao mDao;

        PopulateDbAsync(ExpenseRoomDatabase db) {
            mDao = db.expenseDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mDao.deleteAll();

            Expense expense = new Expense("24/11/2018","7.5", "cine", "");
            mDao.insert(expense);

            return null;
        }
    }
}
