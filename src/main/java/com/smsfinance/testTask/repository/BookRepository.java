package com.smsfinance.testTask.repository;

import com.smsfinance.testTask.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
