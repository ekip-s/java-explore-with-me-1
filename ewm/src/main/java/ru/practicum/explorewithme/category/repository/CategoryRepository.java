package ru.practicum.explorewithme.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.category.model.Category;

/**
 * Интерфейс репозитория для работы с таблицей <b>категорий</b> событий.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
