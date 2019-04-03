package id.salmu.demo.aplikasipelatihan.dao;

import id.salmu.demo.aplikasipelatihan.entity.Materi;
import id.salmu.demo.aplikasipelatihan.entity.Peserta;
import id.salmu.demo.aplikasipelatihan.entity.Sesi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Bang Pardi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/data/peserta.sql", "/data/materi.sql", "/data/sesi.sql"}
)
public class SesiDaoTest {

    @Autowired
    private SesiDao sd;

    @Test
    public void testCariByMateri() {
        Materi m = new Materi();
        m.setId("aa6");

        PageRequest page = new PageRequest(1, 5);

        Page<Sesi> hasilQuery = sd.findByMateri(m, page);
        Assert.assertEquals(1L, hasilQuery.getTotalElements());

        Sesi s = hasilQuery.getContent().get(0);
        Assert.assertNotNull(s);
        Assert.assertEquals("Java Fundamental", s.getMateri().getNama());
    }

    @Test
    public void testCariBerdasarkanTanggalMulaiDanKodeMateri() throws ParseException {
        PageRequest page = new PageRequest(0, 5);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date sejak = formatter.parse("2019-04-01");
        Date sampai = formatter.parse("2019-04-05");

        Page<Sesi> hasil = sd.cariBerdasarkanTanggalMulai(sejak, sampai, "JF-002", page);

        Assert.assertEquals(1L, hasil.getTotalElements());
        Assert.assertFalse(hasil.getContent().isEmpty());

        Sesi s = hasil.getContent().get(0);
        Assert.assertEquals("Java Web", s.getMateri().getNama());

    }
}
