package express.if_week.expresstrain_android;

import android.provider.BaseColumns;

public  final class CreateDB implements BaseColumns {
    public static final String _TABLENAME = "Log";
    public static final String _CREATE =
            "create table "+_TABLENAME+"("
                    +"NickName"+" text primary key, "
                    +"TYPE"+" integer,"
                    +"auto "+"integer);";
}

