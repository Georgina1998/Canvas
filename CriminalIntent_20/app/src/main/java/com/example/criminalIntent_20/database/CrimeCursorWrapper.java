package com.example.criminalIntent_20.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.criminalIntent_20.Crime;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;


public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        String details = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DETAILS));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int time = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TIME));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));
        String sdesc = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SDESC));
        String suspect = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDetails(details);
        crime.setDate(new Date(date));
        crime.setTime(new Time(time));
        crime.setSolved(isSolved != 0);
        crime.setSolveDesc(sdesc);
        crime.setSuspect(suspect);

        return crime;
    }
}