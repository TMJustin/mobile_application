package com.zybooks.capstone_application.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zybooks.capstone_application.dao.ExcursionDAO;
import com.zybooks.capstone_application.dao.VacationDAO;
import com.zybooks.capstone_application.entities.Excursion;
import com.zybooks.capstone_application.entities.Vacation;

@Database(entities = {Vacation.class, Excursion.class}, version = 10, exportSchema = false)
public abstract class VacationDatabaseBuilder extends RoomDatabase {
    public abstract VacationDAO vacationDAO();

    public abstract ExcursionDAO excursionDAO();

    private static volatile VacationDatabaseBuilder INSTANCE;

    static VacationDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VacationDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VacationDatabaseBuilder.class,
                                    "MyVacationDatabase.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration().
                            build();
                }
            }
        }
        return INSTANCE;
    }
}
