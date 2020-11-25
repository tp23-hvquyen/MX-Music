package com.example.mxmusic.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.mxmusic.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class SongLoader {
    public List<SongModel> getAllSong(Context context, String selection) {
        List<SongModel> list = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                "_id",
                "title",
                "artist",
                "album_id",
                "duration",
                "_data",
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, selection, null,
                MediaStore.Audio.Media.TITLE + " ASC");//MediaStore.Audio.Media.IS_MUSIC + "=1"

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String albumID = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String songUri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                SongModel songModel = new SongModel(id, title, singer, albumID, duration, songUri);
                list.add(songModel);
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }
}
