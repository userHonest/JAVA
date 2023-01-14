package Dataset02;

import java.util.ArrayList;

public class Statements {

    public static String sqlInsert() {
        String sql = "INSERT into stemple (dato, dag, tid_jobbet, minutt ) VALUES (?, ?, ?, ?)";
        return sql;
    }
    public static String selectInfo() {
        return "SELECT * FROM stemple";
    }

    public static String getDataById(Integer id) {
        return "SELECT * FROM stemple WHERE time_id=" + id;
    }

    public static String deleteId(int ids) {
        String sql = "DELETE FROM stemple WHERE time_id=" + ids;
        return sql;

    }

    public static String updateMethod(ArrayList<PersonData> id) {

        String updates = "UPDATE stemple SET dato = ?, dag = ? , tid_jobbet = ? , minutt = ?";
        return updates;
    }
}
