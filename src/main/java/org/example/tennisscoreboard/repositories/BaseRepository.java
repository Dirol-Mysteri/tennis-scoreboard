package org.example.tennisscoreboard.repositories;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.exceptions.UniqueConstraintException;
import org.example.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<K extends Serializable, E> implements Repository<K, E> {

    private final Class<E> entityClass;

    protected BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

//    @Override
//    public E save(E entity) {
//        try {
//            Session session = HibernateUtil.getSession();
//            var transaction = session.beginTransaction();
//            session.persist(entity);
//            transaction.commit();
//            session.close();
//        } catch (ConstraintViolationException e) {
//            if (e.getKind() == ConstraintViolationException.ConstraintKind.UNIQUE) {
//                if (entity instanceof Player) {
//                    String playerName = ((Player) entity).getName();
//                    throw new UniqueConstraintException("Player with name " + playerName + " already exists in the database");
//                }
//            }
//        }
//        return entity;
//    }

    @Override
    public E save(E entity) {
        try {
            Session session = HibernateUtil.getCurrentSession();
            session.persist(entity);
        } catch (ConstraintViolationException e) {
            if (e.getKind() == ConstraintViolationException.ConstraintKind.UNIQUE) {
                if (entity instanceof Player) {
                    String playerName = ((Player) entity).getName();
                    throw new UniqueConstraintException("Player with name " + playerName + " already exists in the database");
                }
            }
        }
        return entity;
    }

    @Override
    public void delete(K id) {
        Session session = HibernateUtil.getCurrentSession();
        session.remove(id);
    }

    @Override
    public void update(E entity) {
        Session session = HibernateUtil.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        Session session = HibernateUtil.getCurrentSession();
        var entity = Optional.ofNullable(session.find(entityClass, id));
        return entity;
    }

    @Override
    public List<E> findAll() {
        Session session = HibernateUtil.getCurrentSession();
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(entityClass);
        criteria.from(entityClass);
        var list = session.createQuery(criteria).getResultList();
        return list;
    }
}
