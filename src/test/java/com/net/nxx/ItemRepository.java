package com.net.nxx;

import com.net.nxx.model.NxxIdleItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-07
 */
public interface ItemRepository extends ElasticsearchRepository<NxxIdleItem,Long> {


}
