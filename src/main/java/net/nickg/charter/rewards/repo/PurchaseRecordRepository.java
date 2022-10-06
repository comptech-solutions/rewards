package net.nickg.charter.rewards.repo;

import net.nickg.charter.rewards.domain.PurchaseRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Streamable;

import java.util.List;

public interface PurchaseRecordRepository extends PagingAndSortingRepository<PurchaseRecord, Long> {

    Streamable<PurchaseRecord> findAll();

    Streamable<PurchaseRecord> findAllByCustomerId(Long customerId);
}
