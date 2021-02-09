package utt.doantotnghiep.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utt.doantotnghiep.entities.Config;
import utt.doantotnghiep.service.IConfigService;
import utt.doantotnghiep.service.ServiceCommon;

import java.util.List;


@Service
@Transactional
public class ConfigServiceImpl extends ServiceCommon implements IConfigService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    public String getConfigByKey(String key) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("key").is(key));
            List<Config> configs = mongoTemplate.find(query, Config.class);
            if(configs.size() > 0) {
                return configs.get(0).getValue();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
}
