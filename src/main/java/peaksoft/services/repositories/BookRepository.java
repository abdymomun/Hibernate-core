package peaksoft.services.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import peaksoft.configuration.DataBaseConnection;
import peaksoft.entities.Book;

import java.util.List;
import java.util.Optional;

public class BookRepository implements BookRepo ,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory = DataBaseConnection.createEntityManagerFactory();
    private final SessionFactory sessionFactory = DataBaseConnection.createSessionFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void save(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void saveAllBooks(List<Book> bookList) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        bookList.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Book> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Book> resultList = entityManager.createQuery("""
                select b  from Book b 
                """, Book.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, bookId);
       entityManager.createQuery("""
select b from Book b where b.id = ?1 and b.name=?2
""", Book.class);

        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(book);
    }

    @Override
    public void update(Long id, Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book1 = entityManager.find(Book.class, id);
book1.setName(book.getName());
book1.setAuthor(book.getAuthor());
book1.setPrice(book.getPrice());
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(book);
    }

    @Override
    public void deleteBookById(Long id) {
EntityManager entityManager = entityManagerFactory.createEntityManager();
entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Succesfuly deleted book with id:" + id);
    }

    @Override
    public void clearBooks() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Book> resultList = entityManager.createQuery("""
                select b  from Book b 
                """, Book.class).getResultList();
        for (Book b:resultList) {
            entityManager.remove(b);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Succesfuly  cleaned books");
    }

    @Override
    public void dropTable() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("drop table books").executeUpdate();
            entityManager.getTransaction().commit();

        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.fillInStackTrace();

        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }


    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
        sessionFactory.close();
    }
}
