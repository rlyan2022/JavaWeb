package edu.jlu.fuliang.dao.impl;

import edu.jlu.fuliang.dao.SongDAO;
import edu.jlu.fuliang.domain.Song;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

public class SongDAOImpl extends HibernateDaoSupport implements SongDAO {

    public void delete(long id) {
        Song song = (Song) getHibernateTemplate().get(Song.class, id);
        getHibernateTemplate().delete(song);
    }

    public List<Song> findAll() {
        return (List<Song>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                return session.createCriteria(Song.class).setFetchMode("", FetchMode.JOIN).list();
            }
        });
    }

    public Song findById(long id) {
        return (Song) getHibernateTemplate().get(Song.class, id);
    }

    public List<Song> findByName(String name) {
        return getHibernateTemplate().find("from Song s where s.name like '%" + name + "%'");
    }


    public void save(Song song) {
        getHibernateTemplate().saveOrUpdate(song);
    }


    public void update(Song song) {
        getHibernateTemplate().saveOrUpdate(song);
    }


    public List<Song> findBySingerRegion(final String region) {
        return (List<Song>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createQuery("from Song s left join fetch s.singer where s.singer.region=?");
                return query.setString(0, region).list();
            }
        });
    }
}
