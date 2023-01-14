package Dataset02;

import java.util.Objects;

public class PersonData {




    private int dato;
    private String dag;
    private int tid_jobbet;
    private int minutt;
    public int getMinutt() {
        return minutt;
    }

    public void setMinutt(int minutt) {
        this.minutt = minutt;
    }



    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public String getDag() {
        return dag;
    }

    public void setDag(String dag) {
        this.dag = dag;
    }

    public int getTid_jobbet() {
        return tid_jobbet;
    }

    public void setTid_jobbet(int tid_jobbet) {
        this.tid_jobbet = tid_jobbet;
    }

    @Override
   public boolean equals(Object o) {
        if (this == o ) return true;
        if ( o == null || getClass() != o.getClass()) return false;

        PersonData data = (PersonData) o;
        return Objects.equals(dato,data.dato) && Objects.equals(dag,data.dag) &&
                Objects.equals(tid_jobbet,data.dag) && Objects.equals(minutt,data.minutt);
    }

    @Override
    public  int hashCode() {
        return Objects.hash(dato,dag,tid_jobbet,minutt);
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "dato=" + dato +
                ", dag='" + dag + '\'' +
                ", tid_jobbet=" + tid_jobbet +
                ", minutt=" + minutt +
                '}';
    }

}
