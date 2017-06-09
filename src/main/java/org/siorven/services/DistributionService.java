package org.siorven.services;

import org.siorven.dao.DistributionDao;
import org.siorven.model.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Distribution data access logic
 */
@Service
public class DistributionService {

    @Autowired
    private DistributionDao distributionDao;

    /**
     * Save distribution
     *
     * @param distribution The distribution
     */
    public void save(Distribution distribution) {
        distributionDao.saveDistribution(distribution);
    }

    /**
     * Edit distribution
     *
     * @param distribution The distribution
     */
    public void edit(Distribution distribution) {
        distributionDao.editDistribution(distribution);
    }

    /**
     * Save a distribution if it is new or update it if it exists
     *
     * @param distribution The distribution
     */
    public void saveOrUpdate(Distribution distribution) {
        distributionDao.editOrSaveDistribution(distribution);
    }

    /**
     * Delete distribution
     *
     * @param distribution The distribution
     */
    public void delete(Distribution distribution) {
        distributionDao.deleteDistribution(distribution.getId());
    }

    /**
     * Get distribution that has a given id
     *
     * @param id The id of the distribution
     * @return null if the distribution wasn't found
     */
    public Distribution findById(int id) {
        return distributionDao.findDistributionById(id);
    }

    /**
     * Finds all the distributions
     *
     * @return The list containing all the distributions
     */
    public List<Distribution> findAll() {
        return distributionDao.getAllDistributions();
    }
}

