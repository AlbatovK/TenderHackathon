package com.albatros.springsecurity.data.repository

import com.albatros.springsecurity.data.model.database.Tender
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface TenderSearchRepository : ElasticsearchRepository<Tender, String> {

    @Query(
        """
            { 
                "bool": {
                    "must": [
                        {
                            "multi_match": {
                                "query": "?0",
                                "fields": ["tender_name^5", "tender_id^15", "region^5", "etp^10", "fz", "meta_data", "user_id", "category^10", "customer^15", "date"],
                                "operator": "AND",
                                "fuzziness": "2",
                                "minimum_should_match": "2",
                                "type": "best_fields"
                            }
                        }
                    ],
                    "must_not": [
                        {
                            "multi_match": {
                                "query": "?1",
                                "fields": ["tender_name^5", "tender_id^15", "region^5", "etp^10", "fz", "meta_data", "user_id", "category^10", "customer^15", "date"],
                                "operator": "AND",
                                "fuzziness": "2",
                                "type": "best_fields"
                            }
                        }
                    ]
                }
            }
        """
    )
    fun fullTextSearchAnd(keywords: String): List<Tender>

    @Query(
        """
            { 
                "bool": {
                    "must": [
                        {
                            "multi_match": {
                                "query": "?0",
                                "fields": ["tender_name^5", "tender_id^15", "region^5", "etp^10", "fz", "meta_data", "user_id", "category^10", "customer^15", "date"],
                                "operator": "OR",
                                "fuzziness": "2",
                                "type": "best_fields"
                            }
                        }
                    ],
                    "must_not": [
                        {
                            "multi_match": {
                                "query": "?1",
                                "fields": ["tender_name^5", "tender_id^15", "region^5", "etp^10", "fz", "meta_data", "user_id", "category^10", "customer^15", "date"],
                                "operator": "AND",
                                "fuzziness": "2",
                                "type": "best_fields"
                            }
                        }
                    ]
                }
            }
        """
    )
    fun fullTextSearchOr(keywords: String, exclude: String): List<Tender>
    fun findAllByTenderId(tenderId: String, pageable: Pageable): Page<Tender>

    fun findAllByCustomerContainsIgnoreCase(customer: String, pageable: Pageable): Page<Tender>

    fun findAllByRegionContainsIgnoreCase(region: String, pageable: Pageable): Page<Tender>

    fun findAllByCategory(category: String, pageable: Pageable): Page<Tender>

    fun findAllByEtpEqualsIgnoreCase(etp: String, pageable: Pageable): Page<Tender>

    fun findAllByTenderNameContainingIgnoreCase(query: String, pageable: Pageable): Page<Tender>
}
