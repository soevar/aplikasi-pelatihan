package id.salmu.demo.aplikasipelatihan.controller;

import id.salmu.demo.aplikasipelatihan.dao.PesertaDao;
import id.salmu.demo.aplikasipelatihan.entity.Peserta;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author Bang Pardi
 */
@Controller
@RequestMapping("/peserta")
public class PesertaHtmlController {

    @Autowired
    private PesertaDao pd;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/list")
    public void daftarPeserta(Model m) {
        m.addAttribute("daftarPeserta", pd.findAll());
    }

    @RequestMapping("/hapus")
    public String hapus(@RequestParam(name = "id", required = false) String id) {
        pd.deleteById(id);

        return "redirect:list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String tampilkanForm(
            @RequestParam(value = "id") String id,
            Model m) {
        // defaultnya, isi dengan object baru
        m.addAttribute("peserta", new Peserta());
        
        if (id != null && !id.isEmpty()) {
            Peserta p = pd.findById(id).get();
            if (p != null) {
                m.addAttribute("peserta", p);
            }
        }

        return "peserta/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String prosesForm(@Valid Peserta peserta, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "peserta/form";
        }

        pd.save(peserta);
        status.setComplete();

        return "redirect:list";
    }
}
