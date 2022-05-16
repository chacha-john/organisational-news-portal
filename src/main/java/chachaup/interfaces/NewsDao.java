package chachaup.interfaces;

import chachaup.domain.News;

import java.util.List;

public interface NewsDao {
    //create
    void add(News news);

    //read
    News findById(int id);
    List<News> getAll();
    List<News> findByAuthor(int employeeId);


    //update

    //delete
    void deleteById(int id);
}
