package com.values.LibrarySystemSecurity.service;

import com.values.LibrarySystemSecurity.model.Book;
import com.values.LibrarySystemSecurity.model.Loan;
import com.values.LibrarySystemSecurity.model.User;
import com.values.LibrarySystemSecurity.respository.BookRepository;
import com.values.LibrarySystemSecurity.respository.LoanRepository;
import com.values.LibrarySystemSecurity.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanRepository loanRepository;

    @Override
    public Loan createLoan(Long bookId, String username, LocalDate returnDate) {
        Book book = bookRepository.findBookById(bookId);
        User user = userRepository.findByUsername(username);

        if (book == null || user == null) {
            throw new RuntimeException("Invalid book or user");
        }

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setCheckoutDate(LocalDate.now());
        loan.setReturnDate(returnDate);

        return loan;
    }

    @Override
    public void save(Loan loan) {
        loanRepository.save(loan);
    }

    @Override
    public Loan findLoanById(Long id) {
        return loanRepository.findLoanById(id);
    }
}
