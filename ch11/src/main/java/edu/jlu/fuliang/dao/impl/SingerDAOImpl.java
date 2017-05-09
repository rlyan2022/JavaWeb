package edu.jlu.fuliang.dao.impl;

import edu.jlu.fuliang.dao.SingerDAO;
import edu.jlu.fuliang.domain.Band;
import edu.jlu.fuliang.domain.CompositeSinger;
import edu.jlu.fuliang.domain.Singer;
import edu.jlu.fuliang.domain.SingleSinger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class SingerDAOImpl extends HibernateDaoSupport implements SingerDAO {

    public void deleteSinger(Long id) {
        getHibernateTemplate().delete(id);
    }

    public List<Band> getAllBands() {
        return getHibernateTemplate().find("from Band");
    }

    public List<CompositeSinger> getAllCompositeSingers() {
        return getHibernateTemplate().find("from CompositeSinger");
    }

    public List<Singer> getAllSingers() {
        return getHibernateTemplate().find("from Singer");
    }

    public List<SingleSinger> getAllSingleSingers() {
        return getHibernateTemplate().find("from SingleSinger");
    }

    public List<Band> getBandsByName(String name) {
        return getHibernateTemplate().find("from Band b where b.name=?", new String[]{name});
    }

    public Singer getSingerById(Long id) {
        return (Singer) getHibernateTemplate().get(Singer.class, id);
    }

    public List<Singer> getSingerByRegion(String region) {
        return getHibernateTemplate().find("from Singer s where s.region=?", new String[]{region});
    }

    public List<Singer> getSingersByName(String name) {
        return getHibernateTemplate().find("from Singer s where s.name=?", new String[]{name});
    }

    public List<SingleSinger> getSingleSingersByName(String name) {
        return getHibernateTemplate().find("from SingleSinger s where s.name=?", new String[]{name});
    }

    public void saveSinger(Singer singer) {
        getHibernateTemplate().save(singer);
    }

    public void updateSinger(Singer singer) {
        getHibernateTemplate().saveOrUpdate(singer);
    }
}
