package top.ilovestudy.yinxiang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.ilovestudy.yinxiang.model.entites.Article;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

  @Query(value = "SELECT COUNT(*) AS count,CONCAT(EXTRACT(ISOYEAR FROM created), '-', EXTRACT(MONTH FROM created)) AS groupDate from article where created > (NOW() - INTERVAL '1 YEAR') GROUP by groupDate", nativeQuery = true)
  List<Map> findAllArchiveGroupByMonthAndYearInLastYear();

  @Query(value = "SELECT COUNT(*) AS count,CONCAT(EXTRACT(YEAR FROM created),' ago') AS groupDate FROM article WHERE created < (NOW() - INTERVAL '1 YEAR') GROUP BY groupDate", nativeQuery = true)
  List<Map> findAllArchiveGroupByYearInAYearAgo();

  @Query(value = "SELECT COUNT(*) AS count, c.name, c.id FROM article AS a JOIN category AS c ON a.category_id = c.id GROUP BY c.id", nativeQuery = true)
  List<Map> findAllCategoriesGroup();
}

