/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.salmu.demo.aplikasipelatihan.dao;

import id.salmu.demo.aplikasipelatihan.entity.Peserta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        scripts = "/data/peserta.sql"
)
public class PesertaDaoTest {

    @Autowired
    private PesertaDao pd;

    @Autowired
    private DataSource ds;

    @After
    public void hapusData() throws Exception {
        String sql = "delete from peserta where email='peserta001@gamil.com'";

        try (Connection c = ds.getConnection()) {
            c.createStatement().executeUpdate(sql);
        }
    }

    @Test
    public void testInsert() throws Exception {
        Peserta p = new Peserta();
        p.setNama("Peserta 001");
        p.setEmail("peserta001@gamil.com");
        p.setTanggalLahir(new Date());

        pd.save(p);

        String sql = "select count(*) as jumlah from peserta where email='peserta001@gamil.com'";

        try (Connection c = ds.getConnection()) {
            ResultSet rs = c.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
        }
    }

    @Test
    public void testHitung() {
        Long jumlah = pd.count();

        Assert.assertEquals(3L, jumlah.longValue());
    }

    /*
    @Test
    public void testCariById() {
        Peserta p = pd.findById("aa1");
        Assert.assertNotNull(p);
        Assert.assertEquals("Peserta Test 001", p.getNama());
        Assert.assertEquals("peserta.test.001@gmail.com", p.getEmail());

        Peserta px = pd.findById("xx");
        Assert.assertNull(px);
    }
     */
}
