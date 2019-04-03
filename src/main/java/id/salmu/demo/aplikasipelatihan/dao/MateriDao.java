package id.salmu.demo.aplikasipelatihan.dao;

import id.salmu.demo.aplikasipelatihan.entity.Materi;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Bang Pardi
 */
public interface MateriDao extends PagingAndSortingRepository<Materi, String>{
    
}
