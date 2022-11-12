package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByTitleContainingIgnoreCase(String title);

    @Query("""
            SELECT 
                new br.com.alura.aluraflix.models.video.VideoView(v.title, v.description, v.url, v.category.id)
            FROM Video v
            WHERE v.category.id = :categoryId
            """)
    List<VideoView> findByCategory(@Param("categoryId") Long categoryId);

    default List<VideoView> findByCategory(Category category) {
        return findByCategory(category.getId());
    }
}
