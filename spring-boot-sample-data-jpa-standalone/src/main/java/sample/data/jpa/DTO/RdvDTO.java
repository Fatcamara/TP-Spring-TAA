package sample.data.jpa.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import sample.data.jpa.domain.RdvJPA;

import java.sql.Date;
import java.time.LocalTime;
import java.util.TimeZone;
import java.util.Timer;

/**
 * Classe DTO pour la creation d'un rendez-vous à travers une date, un time, un prof et un étudiant.
 */
public class RdvDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private String time;
    private long idProf;
    private  long idEtd;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getIdProf() {
        return idProf;
    }

    public void setIdProf(long idProf) {
        this.idProf = idProf;
    }

    public long getIdEtd() {
        return idEtd;
    }

    public void setIdEtd(long idEtd) {
        this.idEtd = idEtd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
