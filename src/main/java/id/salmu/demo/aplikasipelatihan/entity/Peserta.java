package id.salmu.demo.aplikasipelatihan.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Bang Pardi
 */
@Entity
@Table(name = "peserta")
@Data
public class Peserta implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    private String nama;

    @Column(nullable = false, unique = true)
    @Email
    @NotNull
    @NotEmpty
    private String email;

    @Column(name = "tanggal_lahir", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    private Date tanggalLahir;
}
