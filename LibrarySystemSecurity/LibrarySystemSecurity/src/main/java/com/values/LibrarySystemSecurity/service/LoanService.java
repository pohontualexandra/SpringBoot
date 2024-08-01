package com.values.LibrarySystemSecurity.service;


import com.values.LibrarySystemSecurity.model.Book;
import com.values.LibrarySystemSecurity.model.Loan;
import com.values.LibrarySystemSecurity.model.User;

import java.time.LocalDate;

public interface LoanService {
    Loan createLoan(Long bookId, String username, LocalDate returnDate);

    void save(Loan loan);

    Loan findLoanById(Long id);
}
