package com.udacity.googleindiascholarships.projects.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ProjectContract {

    public static abstract class ProjectEntry implements BaseColumns {
        public static final String TABLE_NAME = "projectGIS";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PROJECT_NAME = "project_name";
        public static final String COLUMN_PROJECT_DESCRIPTION = "project_description";
        public static final String COLUMN_PROJECT_LOGO_URL = "project_logo_url";
        public static final String COLUMN_PROJECT_GITHUB_URL = "project_github_url";


        //Define the string which contains package name//
        public static final String CONTENT_AUTHORITY = "com.udacity.googleindiascholarships";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_PROJECTS = "project";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PROJECTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROJECTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROJECTS;


    }

}
